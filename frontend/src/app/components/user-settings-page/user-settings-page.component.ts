import {Component} from '@angular/core';
import {StorageService} from "../../_services/storage.service";
import {ApiService} from "../../_services/api/api.service";
import {User} from "../../models/user";
import {AuthService} from "../../_services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-user-settings-page',
  templateUrl: './user-settings-page.component.html',
  styleUrls: ['./user-settings-page.component.css']
})
export class UserSettingsPageComponent {


  // @ts-ignore
  user: User;

  constructor(private storageService: StorageService,
              private apiService: ApiService,
              private authService: AuthService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.apiService.getUserBasicInfo().subscribe((user: User) => {
      this.user = user;
    });
  }


  deleteAccount(): void {
    this.apiService.deleteUser().subscribe();
    this.authService.logout();
    this.storageService.logout();

    setTimeout(() => {
      window.location.href = ""
    }, 2000);

  }


  async updateUser() {
    const {
      firstName,
      lastName,
      completeProjects,
      yearsOfExperience,
      phone,
      location,
      about,
      gitHub,
      linkedin,
      facebook,
      instagram
    } = this.user;
    await this.apiService.updateUser(
      firstName,
      lastName,
      completeProjects,
      yearsOfExperience,
      phone,
      location,
      about,
      gitHub,
      linkedin,
      facebook,
      instagram)
      .subscribe(() => {
        window.location.href = "";
      });
  }
}
