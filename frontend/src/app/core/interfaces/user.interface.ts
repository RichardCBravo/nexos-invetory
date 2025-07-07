import { RoleDto } from './role.interface';

export interface User {
  id: number;
  name: string;
  age: number;
  entryDate: string;
  role: RoleDto;
}
