import { Component, OnInit } from '@angular/core';
import { AuthService } from "../../_services/auth.service";
import { StorageService } from "../../_services/storage.service";
import { Router } from "@angular/router";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {
  form: any = {
    email: null,
    password: null
  };

  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  userNickname: string = '';

  constructor(private authService: AuthService, private storageService: StorageService, private router: Router) {
  }

  ngOnInit(): void {
    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
      this.userNickname = this.storageService.getUserFromSession();
    }
  }

  onSubmit(): void {
    const { email, password } = this.form;

    this.authService.login(email, password).subscribe({
      next: data => {
        this.storageService.saveUser(data.email);
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        // this.roles = this.storageService.getUser().roles;
        this.reloadPage();
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    }
    )
    this.router.navigate(['']);
  }

  reloadPage(): void {
    window.location.reload();
  }

}
