import {Component} from '@angular/core';
import {StorageService} from "../../_services/storage.service";
import {ApiService} from "../../_services/api/api.service";
import {User} from "../../models/user";
import {AuthService} from "../../_services/auth.service";

@Component({
  selector: 'app-user-settings-page',
  templateUrl: './user-settings-page.component.html',
  styleUrls: ['./user-settings-page.component.css']
})
export class UserSettingsPageComponent {
  user: User = new User();

  constructor(private storageService: StorageService,
              private apiService: ApiService,
              private authService: AuthService) {
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

  updateUser() {
    const {
      firstName,
      lastName,
      email,
      gitHubURL,
      linkedInURL,
      facebookURL,
      instagramURL
    } = this.user;

    this.apiService.updateUser(
      firstName,
      lastName,
      email,
      gitHubURL,
      linkedInURL,
      facebookURL,
      instagramURL).subscribe();
  }
}
