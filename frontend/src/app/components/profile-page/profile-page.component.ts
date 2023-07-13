import {Component, OnInit} from '@angular/core';
import {User} from "../../models/user";
import {ProfileService} from "../../_services/profile.service";
import {ActivatedRoute} from '@angular/router';
import {SocialMedia} from "../../models/socialMedia";

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css']
})
export class ProfilePageComponent {
  // @ts-ignore
  username: string | null ="";
  // @ts-ignore
  user: User | undefined;


  constructor(private profileService: ProfileService,
              private activatedroute: ActivatedRoute) {
  }

  ngOnInit() {
    this.username=this.activatedroute.snapshot.paramMap.get("username");
    this.profileService.getUser(this.username)
      .subscribe(user => this.user = user);
  }

  // @ts-ignore
  userInstagram: SocialMedia = this.user.socialMedias
    .find(socialMedia => socialMedia.name == "instagram")
}
