import { Component } from '@angular/core';
import { User } from "../../models/user";
import {ApiService} from "../../_services/api/api.service";

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})

export class MainPageComponent {

  protected User: User | undefined;
  userRole: string = '';

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    this.apiService.getUserBasicInfo().subscribe((user: User) => {
      this.User = user;
      // @ts-ignore
      this.userRole = this.User.roles[0].name;
      console.log(this.userRole)
    });
  }
}

