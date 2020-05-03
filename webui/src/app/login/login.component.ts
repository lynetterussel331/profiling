import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string;
  password: string;

  constructor(
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  login() {
    if (this.username === 'admin' && this.password === 'test') {
      this.router.navigateByUrl('dashboard');
    }
  }

}
