import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../_services/auth.service";

@Component({
  selector: 'app-update-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})

export class ResetPasswordComponent implements OnInit {
  form: any = {
    email: null,
    yearOfBirth: null
  };

  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(private authService: AuthService) {  }

  ngOnInit(): void {

  }

  public onSubmit() {
    const {email, yearOfBirth} = this.form;
    this.authService.resetPassword(email, yearOfBirth).subscribe({
      next: data => {
        this.isSuccessful = true;
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    });
  }
}
