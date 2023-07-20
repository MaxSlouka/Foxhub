import {Component} from '@angular/core';
import {StorageService} from "../../_services/storage.service";
import {ApiService} from "../../_services/api/api.service";
import {User} from "../../models/user";
import {AuthService} from "../../_services/auth.service";
import {Router} from "@angular/router";
import {UploadService} from "../../_services/api/upload.service";
import {Language} from "../../models/language";
import {LanguageService} from "../../_services/language.service";
import {Technology} from "../../models/technology";
import {TechnologyService} from "../../_services/technology.service";

@Component({
  selector: 'app-user-settings-page',
  templateUrl: './user-settings-page.component.html',
  styleUrls: ['./user-settings-page.component.css']
})

export class UserSettingsPageComponent {
  // @ts-ignore
  selectedFile: File = null;


  user: User = {email: "", firstName: "", lastName: "", password: ""};

  // @ts-ignore
  languages: Language[];
  // @ts-ignore
  userLanguages: Language[] | undefined;
  unusedLanguages: Language[] | undefined;

  // @ts-ignore
  technologies: Technology[];
  userTechnologies: Technology[] | undefined;
  unusedTechnologies: Technology[] | undefined;


  constructor(private storageService: StorageService,
              private apiService: ApiService,
              private authService: AuthService,
              private router: Router,
              private uploadService: UploadService,
              private languageService: LanguageService,
              private technologyService: TechnologyService){
  }

  ngOnInit(): void {
    this.apiService.getUserBasicInfo().subscribe((user: User) => {
      this.user = user;
      this.userLanguages = user.languages;
      this.userTechnologies = user.technologies;
    });
    this.languageService.getAll().subscribe((languages: Language[]) => {
      this.languages = languages;
    });
    this.technologyService.getAll().subscribe((technologies: Technology[]) => {
      this.technologies = technologies;
    });
  }

  unusedLanguagesHandle() {
    this.unusedLanguages = this.languages.filter(language => {
      // @ts-ignore
      return !this.userLanguages.some(userLanguage => userLanguage.name === language.name);
    });
  }

  unusedTechnologiesHandle() {
    this.unusedTechnologies = this.technologies.filter(technology => {
      // @ts-ignore
      return !this.userTechnologies.some(userTechnology => userTechnology.name === technology.name);
    });
  }

  handleLanguageComponentEmitter(languages: Language[]) {
    this.user.languages = languages;
  }

  handleTechnologyComponentEmitter(technologies: Technology[]) {
    this.user.technologies = technologies
  }

  // @ts-ignore
  onFileSelected(event) {
    this.selectedFile = <File>event.target.files[0];
  }

  onUpload() {
    this.uploadService.uploadFile(this.selectedFile).subscribe(
      res => {
        this.user.profilePictureUrl = "http://localhost:8080/uploads/" + this.user.nickname + ".jpg"
      },
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
      instagram,
      optionalPage,
      languages
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
      instagram,
      optionalPage,
      languages)
      .subscribe(() => {
        window.location.href = "";
      });
  }
}
