import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customer } from '../models/models';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private baseUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient) {}

  generateToken(username: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/generateToken`, { username, password });
  }

  busLogin(custName: string, phoneNo: string): Observable<Customer> {
    return this.http.post<Customer>(`${this.baseUrl}/login`, { custName, phoneNo });
  }

  saveToken(token: string): void {
    localStorage.setItem('token', token);
  }

  saveCustomer(customer: Customer): void {
    localStorage.setItem('customer', JSON.stringify(customer));
  }

  saveRole(role: string): void {
    localStorage.setItem('role', role);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  getCustomer(): Customer | null {
    const c = localStorage.getItem('customer');
    return c ? JSON.parse(c) : null;
  }

  getRole(): string | null {
    return localStorage.getItem('role');
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }

  isAdmin(): boolean {
    return this.getRole() === 'ROLE_ADMIN';
  }

  logout(): void {
    localStorage.clear();
  }
}
