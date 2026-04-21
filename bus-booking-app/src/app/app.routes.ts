import { Routes } from '@angular/router';
import { authGuard, adminGuard } from './guards/auth.guard';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  {
    path: 'login',
    loadComponent: () =>
      import('./components/login/login.component').then(m => m.LoginComponent)
  },
  {
    path: 'search',
    loadComponent: () =>
      import('./components/search-schedule/search-schedule.component')
        .then(m => m.SearchScheduleComponent),
    canActivate: [authGuard]
  },
  {
    path: 'book/:scheduleId',
    loadComponent: () =>
      import('./components/book-seat/book-seat.component')
        .then(m => m.BookSeatComponent),
    canActivate: [authGuard]
  },
  {
    path: 'my-bookings',
    loadComponent: () =>
      import('./components/my-bookings/my-bookings.component')
        .then(m => m.MyBookingsComponent),
    canActivate: [authGuard]
  },
  {
    path: 'passengers/:bookingId',
    loadComponent: () =>
      import('./components/passengers/passengers.component')
        .then(m => m.PassengersComponent),
    canActivate: [authGuard]
  },
  {
    path: 'admin',
    loadComponent: () =>
      import('./components/admin-dashboard/admin-dashboard.component')
        .then(m => m.AdminDashboardComponent),
    canActivate: [authGuard, adminGuard]
  }
];
