import {Component, OnInit} from '@angular/core';
import {User} from "../../user";
import {ProfileService} from "../../_services/profile.service";

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css']
})
export class ProfilePageComponent {
  // @ts-ignore
  username: string = "max";
  // @ts-ignore
  user: User;

  constructor(private profileService: ProfileService) {
  }

  ngOnInit() {
    this.profileService.getUser(this.username)
      .subscribe(user => this.user = user);
  }
}
