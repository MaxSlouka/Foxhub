import { Component } from '@angular/core';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent {
  form: any = {
    oldPassword: null,
    newPassword:null
  };

  onSubmit() {

  }
}
