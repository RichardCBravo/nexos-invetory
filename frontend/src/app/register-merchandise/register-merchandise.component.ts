import {
  Component,
  effect,
  inject,
  input,
  OnInit,
  output,
} from '@angular/core';
import {
  Merchandise,
  MerchandiseCreate,
  MerchandiseUpdate,
} from '../core/interfaces/merchandise.interface';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { UserService } from '../core/services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register-merchandise',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './register-merchandise.component.html',
  styleUrl: './register-merchandise.component.scss',
})
export class RegisterMerchandiseComponent implements OnInit {
  currentMerchandise = input<Merchandise | null | undefined>(null);

  private fb = inject(FormBuilder);

  private userService = inject(UserService);

  private router = inject(Router);

  users = this.userService.$users;

  createMerchandise = output<MerchandiseCreate>();
  updateMerchandise = output<MerchandiseUpdate>();

  constructor() {
    effect(() => {
      this.initForm();
    });
  }

  ngOnInit(): void {
    this.initForm();
  }

  form = this.fb.group({
    id: [{ value: 0, disabled: true }],
    productName: ['', [Validators.required]],
    quantity: [0, [Validators.required, Validators.min(1)]],
    entryDate: ['', [Validators.required]],
    createdByUser: [{ value: 0, disabled: true }, [Validators.required]],
    updatedByUser: [{ value: 0, disabled: true }, [Validators.required]],
  });

  initForm() {
    this.form.patchValue({
      createdByUser: this.userService.user()?.id || 0,
    });
    if (this.currentMerchandise()) {
      this.form.patchValue({
        id: this.currentMerchandise()?.id || 0,
        productName: this.currentMerchandise()?.productName || '',
        quantity: this.currentMerchandise()?.quantity || 0,
        entryDate: this.currentMerchandise()?.entryDate || '',
        createdByUser:
          this.currentMerchandise()?.createdByUser.id ||
          this.userService.user()?.id ||
          0,
        updatedByUser: this.userService.user()?.id || 0,
      });
    } else {
      this.form.reset();
    }
  }

  onCreateMerchandise() {
    if (this.form.valid) {
      const merchandise: MerchandiseCreate = {
        productName: this.form.value.productName || '',
        quantity: this.form.value.quantity || 0,
        entryDate: this.form.value.entryDate || '',
        createdByUserId: this.userService.user()?.id || 0,
      };
      this.createMerchandise.emit(merchandise);
    }
  }
  onUpdateMerchandise() {
    if (this.form.valid) {
      const merchandise: MerchandiseUpdate = {
        productName: this.form.value.productName || '',
        quantity: this.form.value.quantity || 0,
        entryDate: this.form.value.entryDate || '',
        updatedByUserId: this.userService.user()?.id || 0,
      };
      this.updateMerchandise.emit(merchandise);
    }
  }
  onResetForm() {
    this.router.navigate(['/home']);
    this.form.reset();
    this.initForm();
  }
}
