import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from './components/navbar/navbar.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NavbarComponent, CommonModule],
  template: `
    <app-navbar></app-navbar>
    <div class="page-wrapper">
      <router-outlet></router-outlet>
    </div>
  `,
  styles: [`
    .page-wrapper {
      min-height: calc(100vh - 60px);
      background-color: #f0f4f8;
    }
  `]
})
export class AppComponent {
  title = 'bus-booking-app';
}
