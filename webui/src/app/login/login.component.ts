import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { EncrDecrService } from '../service/encr-decr.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  username: string;
  password: string;
  key: string;

  constructor(
    private router: Router,
    private EncrDecr: EncrDecrService
  ) {
    this.key = 'y7Hfd6Jt9L3b7Cs4xMgTr49vC4h8l3k0==';
  }

  login() {
    const encrUsername = 'nwcsEtO736A4i+KeVvrICw==';
    const encrPassword = 'Ns0Sy8MnfTey2aVi6N8yBA==';
    if (this.EncrDecr.set(this.key, this.username) === encrUsername &&
          this.EncrDecr.set(this.key, this.password) === encrPassword) {
      this.router.navigateByUrl('dashboard');
    }
  }

}
