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
  form:any = {
    firstName: null,
    lastName: null,
    completeProjects: null,
    yearsOfExperience: null,
    phone: null,
    location: null,
    about: null,
    github: null,
    linkedin: null,
    facebook: null,
    instagram: null,
};

  firstName: string = "";
  lastName: string = "";
  email: string = "";
  nickname: string = "";

  constructor(private storageService: StorageService, private apiService: ApiService, private authService: AuthService, private router:Router) {
  }

  ngOnInit(): void {
    this.nickname = this.storageService.getUser();
    this.apiService.getUserBasicInfo().subscribe((user: User) => {
      this.firstName = user.firstName;
      this.form.firstName = user.firstName;
      this.form.lastName = user.lastName;
      this.lastName = user.lastName;
      this.email = user.email;
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
      github,
      linkedin,
      facebook,
      instagram
    } = this.form;
    await this.apiService.updateUser(firstName, lastName, completeProjects, yearsOfExperience, phone, location, about, github, linkedin, facebook, instagram)
      .subscribe(()=>{window.location.href = "";
    });
  }
}
