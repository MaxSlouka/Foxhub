import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../_services/auth.service";
import {StorageService} from "../../_services/storage.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isLoggedIn = false;
  userEmail: string = '';

  constructor(private authService: AuthService, private storageService: StorageService) {
  }

  ngOnInit(): void {
    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
      this.userEmail = this.storageService.getUser();
      // this.roles = this.storageService.getUser().roles;
    }
  }
  logout(): void {
    this.storageService.logout();
    this.authService.logout();

    this.isLoggedIn = false;
  }

}
