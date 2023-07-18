import {Component, OnInit} from '@angular/core';
import {User} from "../../models/user";
import {ProfileService} from "../../_services/profile.service";
import {ActivatedRoute} from '@angular/router';
import {SafeUrl, SafeValue} from "@angular/platform-browser";

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css']
})
export class ProfilePageComponent implements OnInit{
  // @ts-ignore
  username: string | null = "";
  // @ts-ignore
  user: User ;
  qrCodeDownloadLink: SafeValue = "";

  constructor(private profileService: ProfileService,
              private activatedroute: ActivatedRoute,
              ) {
  }

  ngOnInit() {
    this.username = this.activatedroute.snapshot.paramMap.get("username");
    this.profileService.getUser(this.username)
      .subscribe(user => this.user = user)
    // @ts-ignore
    this.onChangeURL();

  }

  onChangeURL(url: SafeValue) {
    let qrCodeURL = 'http://localhost:4200/profile/' + this.username;
    this.qrCodeDownloadLink = url;


    }
}
