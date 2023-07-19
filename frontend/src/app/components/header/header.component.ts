import { Component, OnInit } from '@angular/core';
import { AuthService } from "../../_services/auth.service";
import { StorageService } from "../../_services/storage.service";
import { ProfileService } from "../../_services/profile.service";
import { User } from "../../models/user";
import { ActivatedRoute } from '@angular/router';
import { DataService } from "../../_services/api/data.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})

export class HeaderComponent implements OnInit {

  // @ts-ignore
  username: string | null = "";
  // @ts-ignore
  users: User[];

  isLoggedIn = false;

  userEmail: string = '';

  constructor(private authService: AuthService,
    private profileService: ProfileService,
    private storageService: StorageService,
    private activatedroute: ActivatedRoute,
    public dataService: DataService) {
  }

  ngOnInit(): void {
    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
      this.userEmail = this.storageService.getUserFromSession();
    }

    // was causing error - maybe needed
    // this.username=this.activatedroute.snapshot.paramMap.get("username");
    // this.profileService.getUser(this.username)
    //   .subscribe(user => this.user = user);
  }

  handleDataFromChild(data: any) {
    this.users = data;
  }

  logout(): void {
    this.storageService.logout();
    this.authService.logout();
    this.isLoggedIn = false;
  }
}
