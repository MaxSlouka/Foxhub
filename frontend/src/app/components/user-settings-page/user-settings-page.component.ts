import {Component} from '@angular/core';
import {StorageService} from "../../_services/storage.service";
import {ApiService} from "../../_services/api/api.service";
import {User} from "../../models/user";
import {AuthService} from "../../_services/auth.service";
import {Router} from "@angular/router";
import {UploadService} from "../../_services/api/upload.service";

@Component({
  selector: 'app-user-settings-page',
  templateUrl: './user-settings-page.component.html',
  styleUrls: ['./user-settings-page.component.css']
})
export class UserSettingsPageComponent {
  // @ts-ignore

  selectedFile: File = null;


  user: User = new User();

  constructor(private storageService: StorageService,
              private apiService: ApiService,
              private authService: AuthService,
              private router: Router,
              private uploadService:UploadService) {
  }

  ngOnInit(): void {
    this.apiService.getUserBasicInfo().subscribe((user: User) => {
      this.user = user;
    });
  }

  // @ts-ignore
  onFileSelected(event) {
    this.selectedFile = <File>event.target.files[0];
  }

  onUpload() {
    this.uploadService.uploadFile(this.selectedFile).subscribe(
      res => {},
      err => console.error(err)
    );
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
