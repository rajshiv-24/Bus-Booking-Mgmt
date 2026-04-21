import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { BookingService } from '../../services/booking.service';
import { AuthService } from '../../services/auth.service';
import { PassengerDTO } from '../../models/models';

@Component({
  selector: 'app-book-seat',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './book-seat.component.html'
})
export class BookSeatComponent implements OnInit {
  scheduleId!: number;
  passengers: PassengerDTO[] = [{ passengerName: '', passengerAge: 0, seatNo: '' }];
  loading = false;
  success = false;
  bookingId: number | null = null;
  error = '';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private bookingService: BookingService,
    private authService: AuthService
  ) {}

  ngOnInit() {
    this.scheduleId = Number(this.route.snapshot.paramMap.get('scheduleId'));
  }

  addPassenger() {
    this.passengers.push({ passengerName: '', passengerAge: 0, seatNo: '' });
  }

  removePassenger(i: number) {
    if (this.passengers.length > 1) this.passengers.splice(i, 1);
  }

  confirm() {
    const customer = this.authService.getCustomer();
    if (!customer) { this.router.navigate(['/login']); return; }
    this.loading = true;
    this.error = '';
    this.bookingService.bookSeat({
      scheduleId: this.scheduleId,
      custId: customer.custId,
      passengers: this.passengers
    }).subscribe({
      next: (booking) => {
        this.success = true;
        this.bookingId = booking.id;
        this.loading = false;
      },
      error: (e) => {
        this.error = e.error || 'Booking failed. Please try again.';
        this.loading = false;
      }
    });
  }
}
