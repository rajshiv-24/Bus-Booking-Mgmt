import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { BookingService } from '../../services/booking.service';
import { Passenger } from '../../models/models';

@Component({
  selector: 'app-passengers',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './passengers.component.html'
})
export class PassengersComponent implements OnInit {
  passengers: Passenger[] = [];
  bookingId!: number;
  loading = true;
  error = '';

  constructor(
    private route: ActivatedRoute,
    private bookingService: BookingService
  ) {}

  ngOnInit() {
    this.bookingId = Number(this.route.snapshot.paramMap.get('bookingId'));
    this.bookingService.getPassengersByBooking(this.bookingId).subscribe({
      next: (data) => { this.passengers = data; this.loading = false; },
      error: () => { this.error = 'Failed to load passengers.'; this.loading = false; }
    });
  }
}
