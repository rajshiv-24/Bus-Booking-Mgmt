import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BookingService } from '../../services/booking.service';
import { RouteSchedule, AdminScheduleRequest } from '../../models/models';

@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './admin-dashboard.component.html'
})
export class AdminDashboardComponent implements OnInit {
  schedules: RouteSchedule[] = [];
  loading = true;
  error = '';
  successMsg = '';

  showForm = false;
  isEditing = false;
  editingId: number | null = null;

  form: AdminScheduleRequest = {
    routeId: 1,
    departureTime: '',
    scheduleDt: '',
    avlSeats: 0,
    totSeats: 0,
    schStatus: 'ACTIVE'
  };

  constructor(private bookingService: BookingService) {}

  ngOnInit() { this.loadSchedules(); }

  loadSchedules() {
    this.loading = true;
    this.bookingService.getAllSchedules().subscribe({
      next: (data) => { this.schedules = data; this.loading = false; },
      error: () => { this.error = 'Failed to load schedules.'; this.loading = false; }
    });
  }

  openAddForm() {
    this.isEditing = false;
    this.editingId = null;
    this.form = { routeId: 1, departureTime: '', scheduleDt: '',
                  avlSeats: 0, totSeats: 0, schStatus: 'ACTIVE' };
    this.showForm = true;
    this.error = '';
    this.successMsg = '';
  }

  openEditForm(s: RouteSchedule) {
    this.isEditing = true;
    this.editingId = s.id;
    this.form = {
      routeId: s.busRoute.id,
      departureTime: s.departureTime,
      scheduleDt: s.scheduleDt,
      avlSeats: s.avlSeats,
      totSeats: s.totSeats,
      schStatus: s.schStatus
    };
    this.showForm = true;
    this.error = '';
    this.successMsg = '';
  }

  saveSchedule() {
    this.error = '';
    if (this.isEditing && this.editingId) {
      this.bookingService.updateSchedule(this.editingId, this.form).subscribe({
        next: () => {
          this.successMsg = 'Schedule updated successfully!';
          this.showForm = false;
          this.loadSchedules();
        },
        error: (e) => { this.error = e.error || 'Update failed.'; }
      });
    } else {
      this.bookingService.addSchedule(this.form).subscribe({
        next: () => {
          this.successMsg = 'Schedule added successfully!';
          this.showForm = false;
          this.loadSchedules();
        },
        error: (e) => { this.error = e.error || 'Add failed.'; }
      });
    }
  }

  deleteSchedule(id: number) {
    if (!confirm('Are you sure you want to delete this schedule?')) return;
    this.bookingService.deleteSchedule(id).subscribe({
      next: () => {
        this.successMsg = 'Schedule deleted!';
        this.loadSchedules();
      },
      error: () => { this.error = 'Delete failed.'; }
    });
  }

  cancelForm() {
    this.showForm = false;
    this.error = '';
  }
}
