import { computed, inject, Injectable, signal, effect } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { User } from '../interfaces/user.interface';
import { toSignal } from '@angular/core/rxjs-interop';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private baseUrl = environment.urlBase;
  private http = inject(HttpClient);

  private _users = signal<User[]>([]);

  private _users$ = toSignal<User[] | null>(this.getAllUsers(), {
    initialValue: null,
  });

  public $users = computed(() => {
    return this._users();
  });

  user = signal<User | null>(null);

  constructor() {
    // Use effect to update the _users signal when _users$ changes
    effect(
      () => {
        const fetchedUsers = this._users$();
        if (fetchedUsers && this._users().length === 0) {
          this._users.set(fetchedUsers);
        }
      },
      {
        allowSignalWrites: true,
      }
    );
  }

  getAllUsers() {
    return this.http.get<User[]>(`${this.baseUrl}/user`);
  }
}
