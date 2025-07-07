import {
  ChangeDetectionStrategy,
  Component,
  inject,
  input,
  model,
  output,
} from '@angular/core';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { Params, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { timer } from 'rxjs';

@Component({
  selector: 'app-search-merchandise',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './search-merchandise.component.html',
  styleUrl: './search-merchandise.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class SearchMerchandiseComponent {
  private fb = inject(FormBuilder);
  private router = inject(Router);

  productName = model<string>('');
  userId = model<number>(0);
  searchForm = this.fb.group({
    productName: [this.productName()],
    userId: [this.userId()],
  });

  searchMerchandise = output<void>();

  onSearch(): void {
    const params: Params = {};
    if (this.searchForm.value.productName) {
      params['productName'] = this.searchForm.value.productName.trim() || null;
      params['userId'] = this.searchForm.value.userId || null;
    }

    this.router.navigate(['home'], {
      queryParams: params,
      queryParamsHandling: 'merge',
    });
    timer(200).subscribe(() => {
      this.productName.set(this.searchForm.value.productName || '');
      this.userId.set(this.searchForm.value.userId || 0);
      this.searchMerchandise.emit();
    });
  }
}
