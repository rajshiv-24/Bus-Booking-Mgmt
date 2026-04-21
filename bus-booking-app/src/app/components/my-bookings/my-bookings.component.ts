import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { BookingService } from '../../services/booking.service';
import { AuthService } from '../../services/auth.service';
import { BusBooking } from '../../models/models';

@Component({
  selector: 'app-my-bookings',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './my-bookings.component.html'
})
export class MyBookingsComponent implements OnInit {
  bookings: BusBooking[] = [];
  loading = true;
  error = '';

  constructor(
    private bookingService: BookingService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit() {
    const customer = this.authService.getCustomer();
    if (!customer) { this.router.navigate(['/login']); return; }
    this.bookingService.getBookingsByCustomer(customer.custId).subscribe({
      next: (data) => { this.bookings = data; this.loading = false; },
      error: () => { this.error = 'Failed to load bookings.'; this.loading = false; }
    });
  }

  viewPassengers(bookingId: number) {
    this.router.navigate(['/passengers', bookingId]);
  }
}
