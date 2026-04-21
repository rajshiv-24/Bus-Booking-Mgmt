import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html'
})
export class LoginComponent {
  username = '';
  password = '';
  custName = '';
  phoneNo = '';
  step = 1;
  loading = false;
  error = '';

  constructor(private authService: AuthService, private router: Router) {}

  onJwtLogin() {
    if (!this.username || !this.password) {
      this.error = 'Please enter username and password.';
      return;
    }
    this.loading = true;
    this.error = '';
    this.authService.generateToken(this.username, this.password).subscribe({
      next: (res) => {
        this.authService.saveToken(res.token);
        const role = this.username === 'admin' ? 'ROLE_ADMIN' : 'ROLE_USER';
        this.authService.saveRole(role);
        this.loading = false;
        if (role === 'ROLE_ADMIN') {
          this.router.navigate(['/admin']);
        } else {
          this.step = 2;
        }
      },
      error: () => {
        this.error = 'Invalid username or password.';
        this.loading = false;
      }
    });
  }

  onBusLogin() {
    if (!this.custName || !this.phoneNo) {
      this.error = 'Please enter name and phone number.';
      return;
    }
    this.loading = true;
    this.error = '';
    this.authService.busLogin(this.custName, this.phoneNo).subscribe({
      next: (customer) => {
        this.authService.saveCustomer(customer);
        this.loading = false;
        this.router.navigate(['/search']);
      },
      error: () => {
        this.error = 'Invalid name or phone number.';
        this.loading = false;
      }
    });
  }
}
