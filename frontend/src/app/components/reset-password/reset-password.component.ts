import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../_services/auth.service";
import {StorageService} from "../../_services/storage.service";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-update-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})

export class ResetPasswordComponent implements OnInit{
  form: any = {
    email: null
  };

  constructor(
    private authService: AuthService
  ) { }

  ngOnInit(): void {

  }
public onSubmit(){
  const { email } = this.form;
  this.authService.resetPassword(email).subscribe();
}

}
