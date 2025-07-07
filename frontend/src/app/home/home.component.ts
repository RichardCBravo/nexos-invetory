import {
  ChangeDetectionStrategy,
  Component,
  computed,
  effect,
  inject,
  input,
  OnInit,
  signal,
} from '@angular/core';
import { RegisterMerchandiseComponent } from '../register-merchandise/register-merchandise.component';
import { SearchMerchandiseComponent } from '../search-merchandise/search-merchandise.component';
import { ListMerchandiseComponent } from '../list-merchandise/list-merchandise.component';
import {
  Merchandise,
  MerchandiseCreate,
  MerchandiseUpdate,
} from '../core/interfaces/merchandise.interface';
import { InventoryService } from '../core/services/inventory.service';
import { toSignal } from '@angular/core/rxjs-interop';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    RegisterMerchandiseComponent,
    SearchMerchandiseComponent,
    ListMerchandiseComponent,
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class HomeComponent implements OnInit {
  id = input<number | null>(null);
  productName = input<string | null>(null);
  userId = input<number | null>(null);

  private inventoryService = inject(InventoryService);
  private router = inject(Router);

  // Use computed to reactively track the id and fetch when needed
  currentMerchandise = signal<Merchandise | null>(null);

  listMerchandise = signal<Merchandise[]>([]);

  searchMerchandise = signal<Merchandise[]>([]);

  constructor() {
    effect(
      () => {
        const currentId = this.id();
        if (currentId !== null) {
          this.fetchMerchandiseById(currentId);
        } else {
          this.currentMerchandise.set(null);
        }
      },
      {
        allowSignalWrites: true,
      }
    );
  }
  ngOnInit(): void {
    this.getAllMerchandise();

    if (this.id()) {
      this.fetchMerchandiseById(this.id());
    }
  }

  // Method to fetch merchandise by id and update the signal
  private fetchMerchandiseById(id: number | null): void {
    if (!id) {
      this.currentMerchandise.set(null);
      return;
    }
    this.inventoryService.getMerchandiseById(id).subscribe({
      next: (merchandise: Merchandise) => {
        this.currentMerchandise.set(merchandise);
      },
      error: (error) => {
        console.error('Error fetching merchandise by ID:', error);
        this.currentMerchandise.set(null);
        this.router.navigate(['/home']);
      },
    });
  }

  createMerchandise(merchandise: MerchandiseCreate) {
    this.inventoryService.registerMerchandise(merchandise).subscribe({
      next: (response: Merchandise) => {
        console.log('Merchandise registered successfully:', response);
        this.listMerchandise.update((currentList) => [
          ...currentList,
          response,
        ]);
      },
      error: (error) => {
        console.error('Error registering merchandise:', error);
      },
    });
  }

  updateMerchandise(id: number, merchandise: MerchandiseUpdate) {
    this.inventoryService.updateMerchandise(id, merchandise).subscribe({
      next: (response: Merchandise) => {
        console.log('Merchandise updated successfully:', response);
        this.listMerchandise.update((currentList) => {
          const index = currentList.findIndex((m) => m.id === response.id);
          if (index !== -1) {
            currentList[index] = response; // Update the existing merchandise
          }
          return [...currentList];
        });
      },
      error: (error) => {
        console.error('Error updating merchandise:', error);
      },
    });
  }

  deleteMerchandise(id: number) {
    this.inventoryService.deleteMerchandise(id).subscribe({
      next: () => {
        console.log('Merchandise deleted successfully');
        this.listMerchandise.update((currentList) =>
          currentList.filter((m) => m.id !== id)
        );
      },
      error: (error) => {
        console.error('Error deleting merchandise:', error);
      },
    });
  }

  getAllMerchandise() {
    this.inventoryService.getMerchandise().subscribe({
      next: (response: Merchandise[]) => {
        console.log('Merchandise fetched successfully:', response);
        this.listMerchandise.set(response);
      },
      error: (error) => {
        console.error('Error fetching merchandise:', error);
      },
    });
  }

  getMerchandiseSearch() {
    this.searchMerchandise.set([]);

    this.inventoryService
      .getMerchandiseSearch(
        this.productName() || undefined,
        this.userId() || undefined
      )
      .subscribe({
        next: (response: Merchandise[]) => {
          console.log('Merchandise search results:', response);
          this.searchMerchandise.set(response);
        },
        error: (error) => {
          console.error('Error searching merchandise:', error);
        },
      });
  }
}
