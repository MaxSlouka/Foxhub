import { Component } from '@angular/core';
import { StorageService } from "../../_services/storage.service";
import { ApiService } from "../../_services/api/api.service";
import { User } from "../../models/user";
import { AuthService } from "../../_services/auth.service";
import { Router } from "@angular/router";
import { UploadService } from "../../_services/api/upload.service";
import { Language } from "../../models/language";
import { LanguageService } from "../../_services/language.service";
import { Technology } from "../../models/technology";
import { TechnologyService } from "../../_services/technology.service";
import { PersonalityService } from "../../_services/personality.service";
import { Personality } from "../../models/personality";
import { ColorPersonality } from 'src/app/models/colorPersonality';
import { ColorPersonalityService } from "../../_services/color-personality.service";

@Component({
  selector: 'app-user-settings-page',
  templateUrl: './user-settings-page.component.html',
  styleUrls: ['./user-settings-page.component.css']
})

export class UserSettingsPageComponent {
  // @ts-ignore
  selectedFile: File = null;
  profileProgress: number = 0;
  user: User = { email: "", firstName: "", lastName: "", password: "" };

  // @ts-ignore
  private oneLineAbout: string;

  // @ts-ignore
  private workLocation: string;

  // @ts-ignore
  languages: Language[];
  userLanguages: Language[] | undefined;
  unusedLanguages: Language[] | undefined;
  languageInput: string = "";

  // @ts-ignore
  technologies: Technology[];
  userTechnologies: Technology[] | undefined;
  unusedTechnologies: Technology[] | undefined;

  // @ts-ignore
  personalities: Personality[];

  // @ts-ignore
  colorPersonalities: ColorPersonality[];

  constructor(
    private storageService: StorageService,
    private apiService: ApiService,
    private authService: AuthService,
    private router: Router,
    private uploadService: UploadService,
    private languageService: LanguageService,
    private technologyService: TechnologyService,
    private personalityService: PersonalityService,
    private colorPersonalityService: ColorPersonalityService
  ) {
  }

  ngOnInit()
    :
    void {
    this.apiService.getUserBasicInfo().subscribe((user: User) => {
      this.user = user;
      this.userLanguages = user.languages;
      this.userTechnologies = user.technologies;
      this.profileProgress = this.setProgress();
    });
    this.languageService.getAll().subscribe((languages: Language[]) => {
      this.languages = languages;
    });
    this.technologyService.getAll().subscribe((technologies: Technology[]) => {
      this.technologies = technologies;
    });
    this.personalityService.getAll().subscribe((personalities: Personality[]) => {
      this.personalities = personalities;
    });
    console.log(this.user);
  }

  onWorkPreferenceChange() {
    console.log('Work Preference changed:', this.user.workPreference);
  }

  setWorkPreference(clickedWorkPreference: string) {
    this.user.workPreference = clickedWorkPreference;
  }

  onPersonalitySelect(event
    :
    any
  ) {
    const selectedPersonalityId = +event.target.value;
    this.user.personality = this.personalities.find(p => p.id === selectedPersonalityId);
  }

  onColorPersonalitySelect(event
    :
    any
  ) {
    const selectedColorPersonalityId = +event.target.value;
    this.user.colorPersonality = this.colorPersonalities.find(p => p.id === selectedColorPersonalityId);
  }

  setProgress()
    :
    number {
    let filledFields: number = 0;
    let totalFields: number = Object.keys(this.user).length;

    for (let prop in this.user) {
      // @ts-ignore
      if (this.user[prop] !== null) {
        filledFields++;
      }
    }
    return Math.round((filledFields / totalFields) * 100);
  }

  public searchLanguage(key
    :
    string
  ):
    void {
    const results
      :
      Language[] = [];

    for (const language of this.languages) {
      if (language.name.toLowerCase().includes(key.toLowerCase())) {
        results.push(language);
      }
    }
    this.unusedLanguages = results;
  }

  public searchTechnology(key
    :
    string
  ):
    void {
    const results
      :
      Technology[] = [];

    for (const technology of this.technologies) {
      if (technology.name.toLowerCase().includes(key.toLowerCase())) {
        results.push(technology);
      }
    }
    this.unusedTechnologies = results;
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

  handleLanguageComponentEmitter(languages
    :
    Language[]
  ) {
    this.user.languages = languages;
  }

  handleTechnologyComponentEmitter(technologies
    :
    Technology[]
  ) {
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

  deleteAccount()
    :
    void {
    this.apiService.deleteUser().subscribe();
    this.authService.logout();
    this.storageService.logout();

    setTimeout(() => {
      window.location.href = ""
    }
      ,
      2000
    )
      ;
  }

  async updateUser() {
    // @ts-ignore
    const {
      firstName,
      lastName,
      phone,
      location,
      workLocation,
      oneLineAbout,
      workPreference,
      about,
      gitHub,
      linkedin,
      facebook,
      instagram,
      optionalPage,
      languages,
      technologies,
      personality,
      colorPersonality,
      yearOfBirth,
      workStatus
    } = this.user;
    await this.apiService.updateUser(
      firstName,
      lastName,
      phone,
      location,
      workLocation,
      oneLineAbout,
      workPreference,
      about,
      gitHub,
      linkedin,
      facebook,
      instagram,
      optionalPage,
      languages,
      technologies,
      personality,
      colorPersonality,
      yearOfBirth,
      workStatus
    )
      .subscribe(() => {
        this.router.navigate(['/profile', this.user.nickname]);

      });
  }
}
