import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-update-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})

export class ResetPasswordComponent implements OnInit{
  form: any = {
    email: null
  };

  ngOnInit(): void {

  }
public onSubmit(){

}

}
