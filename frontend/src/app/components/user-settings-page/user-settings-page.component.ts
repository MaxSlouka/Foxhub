import { Component } from '@angular/core';
import {StorageService} from "../../_services/storage.service";
import {RequestsService} from "../../_services/requests/requests.service";
import {User} from "../../models/user";

@Component({
  selector: 'app-user-settings-page',
  templateUrl: './user-settings-page.component.html',
  styleUrls: ['./user-settings-page.component.css']
})
export class UserSettingsPageComponent {

firstName: string = "";
lastName: string = "";
email: string = "";
nickname: string = "";

  constructor(private storageService: StorageService, private requestsService:RequestsService) { }
  ngOnInit(): void {
    this.nickname = this.storageService.getUser();
    this.requestsService.getUserBasicInfo().subscribe((user: User) => {
      this.firstName = user.firstName;
      this.lastName = user.lastName;
      this.email = user.email;
    });

  }



  deleteAccount(): void {
    //todo: implement
  }

}
