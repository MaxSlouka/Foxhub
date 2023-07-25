import { Component } from '@angular/core';
import { User } from "../../models/user";
import { ProfileService } from "../../_services/profile.service";
import { ActivatedRoute, Router, NavigationEnd } from '@angular/router';

import { SafeUrl, SafeValue } from "@angular/platform-browser";
import { filter } from 'rxjs/operators';
import {StorageService} from "../../_services/storage.service";

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css']
})
export class ProfilePageComponent {
  // @ts-ignore
  username: string | null = "";
  user: User = { email: "", firstName: "", lastName: "", password: "" };
  qrCodeDownloadLink: SafeValue = "";
  isOpen: boolean = false;
  isLoggedIn: boolean = false;

  constructor(
    private profileService: ProfileService,
    private activatedroute: ActivatedRoute,
    private storageService: StorageService,
    private router: Router
  ) {
    router.events.pipe(
      filter(event => event instanceof NavigationEnd)
    ).subscribe(event => {
      this.ngOnInit();
    });
  }

  ngOnInit() {
    this.username = this.activatedroute.snapshot.paramMap.get("username");
    this.profileService.getUser(this.username)
      .subscribe(user => this.user = user)
    // @ts-ignore
    this.onChangeURL();
    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
    }
  }

  toggleSocialLinks() {
    this.isOpen = !this.isOpen;
  }

  onChangeURL(url: SafeValue) {
    let qrCodeURL = 'http://localhost:4200/profile/' + this.username;
    this.qrCodeDownloadLink = url;
  }
}
