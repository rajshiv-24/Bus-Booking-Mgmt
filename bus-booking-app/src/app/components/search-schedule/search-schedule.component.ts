import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { BookingService } from '../../services/booking.service';
import { RouteSchedule } from '../../models/models';

@Component({
  selector: 'app-search-schedule',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './search-schedule.component.html'
})
export class SearchScheduleComponent {
  src = '';
  dest = '';
  schedules: RouteSchedule[] = [];
  searched = false;
  loading = false;
  error = '';

  constructor(private bookingService: BookingService, private router: Router) {}

  search() {
    if (!this.src.trim() || !this.dest.trim()) {
      this.error = 'Please enter both source and destination.';
      return;
    }
    this.loading = true;
    this.error = '';
    this.searched = false;
    this.bookingService.getSchedules(this.src.trim(), this.dest.trim()).subscribe({
      next: (data) => {
        this.schedules = data;
        this.searched = true;
        this.loading = false;
      },
      error: () => {
        this.error = 'Failed to fetch schedules. Please try again.';
        this.loading = false;
      }
    });
  }

  bookNow(scheduleId: number) {
    this.router.navigate(['/book', scheduleId]);
  }
}
