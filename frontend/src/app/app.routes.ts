import {Routes} from '@angular/router';

export const routes: Routes = [
  { path: '', loadComponent: () => import('./home/home-component/home.component').then(m => m.HomeComponent) },

  { path: 'login', loadComponent: () => import('./login-component/login-component').then(m => m.LoginComponent) },
  { path: 'register', loadComponent: () => import('./register-component/register-component').then(m => m.RegisterComponent) },
  { path: '**', redirectTo: '' }
];
