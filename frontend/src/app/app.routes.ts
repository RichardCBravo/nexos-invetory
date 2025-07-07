import { Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';

export const routes: Routes = [
  {
    path: 'home',
    loadComponent: () =>
      import('./home/home.component').then((m) => m.HomeComponent),
  },
  {
    path: 'home/:id',
    loadComponent: () =>
      import('./home/home.component').then((m) => m.HomeComponent),
  },

  {
    path: '**',
    redirectTo: 'home',
    pathMatch: 'full',
  },
];
