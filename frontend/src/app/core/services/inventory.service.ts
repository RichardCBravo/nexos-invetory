import { HttpClient, HttpParams } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import {
  Merchandise,
  MerchandiseCreate,
  MerchandiseUpdate,
} from '../interfaces/merchandise.interface';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root',
})
export class InventoryService {
  private baseUrl = environment.urlBase;

  private http = inject(HttpClient);
  private userService = inject(UserService);

  getMerchandise() {
    return this.http.get<Merchandise[]>(`${this.baseUrl}/merchandise`);
  }
  getMerchandiseById(id: number) {
    return this.http.get<Merchandise>(`${this.baseUrl}/merchandise/${id}`);
  }

  registerMerchandise(merchandise: MerchandiseCreate) {
    return this.http.post<Merchandise>(
      `${this.baseUrl}/merchandise`,
      merchandise
    );
  }

  updateMerchandise(id: number, merchandise: MerchandiseUpdate) {
    return this.http.put<Merchandise>(
      `${this.baseUrl}/merchandise/${id}`,
      merchandise
    );
  }

  deleteMerchandise(id: number) {
    return this.http.delete<void>(`${this.baseUrl}/merchandise/${id}`, {
      params: { userId: this.userService.user()?.id?.toString() || '' },
    });
  }

  getMerchandiseSearch(productName?: string, userId?: number) {
    let params: { [param: string]: string } = {};
    if (productName && productName.trim()) {
      params['productName'] = productName.trim();
    }
    if (userId !== undefined && userId !== null) {
      params['userId'] = userId.toString();
    }
    return this.http.get<Merchandise[]>(`${this.baseUrl}/merchandise/search`, {
      params,
    });
  }
}
