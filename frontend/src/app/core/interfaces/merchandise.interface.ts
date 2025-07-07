import { User } from './user.interface';

export interface MerchandiseCreate {
  productName: string;
  quantity: number;
  entryDate: string;
  createdByUserId: number;
}

export interface MerchandiseUpdate {
  productName: string;
  quantity: number;
  entryDate: string;
  updatedByUserId: number;
}

export interface Merchandise {
  id: number;
  productName: string;
  quantity: number;
  entryDate: string;
  updateDate?: string;
  createdByUser: User;
  updatedByUser?: User;
}
