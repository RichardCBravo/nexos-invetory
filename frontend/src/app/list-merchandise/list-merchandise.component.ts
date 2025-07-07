import {
  ChangeDetectionStrategy,
  Component,
  inject,
  input,
  output,
} from '@angular/core';
import { InventoryService } from '../core/services/inventory.service';
import { Merchandise } from '../core/interfaces/merchandise.interface';
import { Router } from '@angular/router';
import { DatePipe, NgClass, NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-list-merchandise',
  standalone: true,
  imports: [DatePipe],
  templateUrl: './list-merchandise.component.html',
  styleUrl: './list-merchandise.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ListMerchandiseComponent {
  listMerchandise = input<Merchandise[]>([]);

  private inventoryService = inject(InventoryService);
  private router = inject(Router);

  deleteMerchandise = output<number>();

  onDeleteMerchandise(id: number): void {
    this.deleteMerchandise.emit(id);
  }

  onEditMerchandise(merchandise: Merchandise): void {
    this.router.navigate(['home']);
    this.router.navigate(['home', merchandise.id]);
  }
}
