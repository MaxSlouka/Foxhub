import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../_services/auth.service";
import {StorageService} from "../../_services/storage.service";
import {ProfileService} from "../../_services/profile.service";
import {User} from "../../models/user";
import {ActivatedRoute} from '@angular/router';
import {PeopleService} from "../../_services/people.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  // @ts-ignore
  username: string | null = "";
  // @ts-ignore
  user: User;
  users: User[] = [];
  isLoggedIn = false;

  userEmail: string = '';

  constructor(private authService: AuthService,
              private profileService: ProfileService,
              private storageService: StorageService,
              private activatedroute: ActivatedRoute,
              private peopleService: PeopleService) {
  }

  ngOnInit(): void {
    this.getUsers();
    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
      this.userEmail = this.storageService.getUser();
      // this.roles = this.storageService.getUser().roles;
      console.log(this.user)
    }

    this.username = this.activatedroute.snapshot.paramMap.get("username");
    this.profileService.getUser(this.username)
      .subscribe(user => this.user = user);
  }

  logout(): void {
    this.storageService.logout();
    this.authService.logout();

    this.isLoggedIn = false;
  }


  private getUsers(): void {
    this.peopleService
      .getAll().subscribe((users) =>
      this.users = users
    );
  }

}
