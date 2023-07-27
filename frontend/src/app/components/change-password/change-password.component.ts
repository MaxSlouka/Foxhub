import { Component } from '@angular/core';
import { AuthService } from "../../_services/auth.service";

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})

export class ChangePasswordComponent {
  form: any = {

    newPassword: null
  }

  constructor(private authService: AuthService) {
  }

  onSubmit() {
    const { newPassword } = this.form;
    this.authService.changePassword(newPassword).subscribe();
  }
}
