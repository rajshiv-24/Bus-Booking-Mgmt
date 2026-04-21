import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RouteSchedule, BookingRequest, BusBooking, Passenger, AdminScheduleRequest } from '../models/models';
import { AuthService } from './auth.service';

@Injectable({ providedIn: 'root' })
export class BookingService {
  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient, private authService: AuthService) {}

  private getHeaders(): HttpHeaders {
    return new HttpHeaders({
      'Authorization': `Bearer ${this.authService.getToken()}`,
      'Content-Type': 'application/json'
    });
  }

  getSchedules(src: string, dest: string): Observable<RouteSchedule[]> {
    const params = new HttpParams().set('src', src).set('dest', dest);
    return this.http.get<RouteSchedule[]>(
      `${this.baseUrl}/schedules/search`,
      { headers: this.getHeaders(), params }
    );
  }

  bookSeat(request: BookingRequest): Observable<BusBooking> {
    return this.http.post<BusBooking>(
      `${this.baseUrl}/bookings/book`,
      request, { headers: this.getHeaders() }
    );
  }

  getBookingsByCustomer(custId: number): Observable<BusBooking[]> {
    return this.http.get<BusBooking[]>(
      `${this.baseUrl}/bookings/customer/${custId}`,
      { headers: this.getHeaders() }
    );
  }

  getPassengersByBooking(bookingId: number): Observable<Passenger[]> {
    return this.http.get<Passenger[]>(
      `${this.baseUrl}/bookings/${bookingId}/passengers`,
      { headers: this.getHeaders() }
    );
  }

  getAllSchedules(): Observable<RouteSchedule[]> {
    return this.http.get<RouteSchedule[]>(
      `${this.baseUrl}/admin/schedules`,
      { headers: this.getHeaders() }
    );
  }

  addSchedule(request: AdminScheduleRequest): Observable<RouteSchedule> {
    return this.http.post<RouteSchedule>(
      `${this.baseUrl}/admin/schedules`,
      request, { headers: this.getHeaders() }
    );
  }

  updateSchedule(id: number, request: AdminScheduleRequest): Observable<RouteSchedule> {
    return this.http.put<RouteSchedule>(
      `${this.baseUrl}/admin/schedules/${id}`,
      request, { headers: this.getHeaders() }
    );
  }

  deleteSchedule(id: number): Observable<string> {
    return this.http.delete(
      `${this.baseUrl}/admin/schedules/${id}`,
      { headers: this.getHeaders(), responseType: 'text' }
    );
  }
}
