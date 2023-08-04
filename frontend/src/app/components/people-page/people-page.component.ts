import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { Technology } from "../../models/technology";
import { TechnologyService } from "../../_services/technology.service";
import { ApiService } from "../../_services/api/api.service";
import { User } from "../../models/user";
import { Language } from "../../models/language";
import { LanguageService } from "../../_services/language.service";
import { Personality } from "../../models/personality";
import { PersonalityService } from "../../_services/personality.service";
import { CookieService } from 'ngx-cookie-service';
import { ColorPersonality } from 'src/app/models/colorPersonality';
import { ColorPersonalityService } from 'src/app/_services/color-personality.service';

@Component({
  selector: 'app-people-page',
  templateUrl: './people-page.component.html',
  styleUrls: ['./people-page.component.css']
})

export class PeoplePageComponent implements OnInit {
  @ViewChild('customRange3', { static: true }) rangeInputRef!: ElementRef<HTMLInputElement>;
  @ViewChild('rangeValue', { static: true }) rangeValueRef!: ElementRef<HTMLSpanElement>;

  technologies: Technology[] = [];
  languages: Language[] = [];
  personalities: Personality[] = [];
  colorPersonalities: ColorPersonality[] = [];
  addedUsers: User[] = [];
  usedTechnologies: Technology[] = [];
  usedLanguages: Language[] = [];
  usedPersonalities: Personality[] = [];
  usedColorPersonalities: ColorPersonality[] = [];

  selectedTechnologies: string[] = [];
  selectedLanguages: string[] = [];
  selectedPersonalities: string[] = [];
  selectAllPersonalities: boolean = true;
  selectedColorPersonalities: string[] = [];
  selectAllColorPersonalities: boolean = true;
  selectedAges: string[] = [];
  workStatus: any;

  // @ts-ignore
  users: User[] = [];
  verifiedAndUsersOnly: User[] = [];
  nonFilteredUsers: User[] = [];
  filterContentExpanded: boolean = true;
  // @ts-ignore
  filterWorkStatus: string;

  actualTechnologyValue: string[] = [];
  actualLanguageValue: string[] = [];
  actualPersonalityValue: string[] = [];
  actualColorPersonalityValue: string[] = [];

  actualAgeValue: string = '';
  actualWorkStatusValue: string = '';

  restPersonalityFilter: User[] = [];
  restColorPersonalityFilter: User[] = [];
  restOpenToWorkFilter: User[] = [];
  restAgeFilter: User[] = [];
  restLanguageFilter: User[] = [];
  restTechnologiesFilter: User[] = [];
  public showCookiePopup = false;

  constructor(
    private technologyService: TechnologyService,
    private languageService: LanguageService,
    private apiService: ApiService,
    private personalityService: PersonalityService,
    private colorPersonalityService: ColorPersonalityService,
    private cookieService: CookieService) {

  }

  ngOnInit(): void {
    this.filterWorkStatus = 'all';
    this.showCookiePopup = this.cookieService.get('cookie_consent') !== 'true';

    this.technologyService.getAll().subscribe(technologies => {
      this.technologies = technologies;
    });
    this.languageService.getAll().subscribe(languages => {
      this.languages = languages;
    });
    this.personalityService.getAll().subscribe(personalities => {
      this.personalities = personalities
    });
    this.colorPersonalityService.getAll().subscribe(colorPersonalities => {
      this.colorPersonalities = colorPersonalities
    });

    // @ts-ignore
    this.apiService.getAll().subscribe(users => {
      this.users = users.filter((user: User) => user.verified && user.role?.name === "USER");
      this.verifiedAndUsersOnly = users.filter((user: User) => user.verified && user.role?.name === "USER");
      this.nonFilteredUsers = this.verifiedAndUsersOnly;
      this.usedTechnologiesList();
      this.usedLanguagesList();
      this.usedPersonalitiesList();
      this.usedColorPersonalitiesList();
      for (let user of this.users) {
        if (this.addedUsers.includes(user)) {
          user.inCart = true;
        }
      }
    });
  }

  acceptCookies() {
    this.cookieService.set('cookie_consent', 'true');
    this.showCookiePopup = false;
  }

  declineCookies() {
    this.cookieService.delete('cookie_consent');
    this.showCookiePopup = false;
  }

  get filteredUsers(): any[] {
    // @ts-ignore
    return this.nonFilteredUsers.filter(user => user.outOfFilters?.length > 4);
  }

  toggleFilterContent(): void {
    this.filterContentExpanded = !this.filterContentExpanded;
  }

  usedTechnologiesList() {
    const usedTechNames: string[] = [];
    for (let user of this.users) {
      // @ts-ignore
      for (let tech of user.technologies) {
        const techName = tech.name.toLowerCase();
        if (!usedTechNames.includes(techName)) {
          usedTechNames.push(techName);
          this.usedTechnologies.push(tech);
        }
      }
    }
  }

  // @ts-ignore
  usedLanguagesList(): Language[] {
    const usedLangNames: string[] = [];
    for (let user of this.users) {
      // @ts-ignore
      for (let lang of user.languages) {
        const langName = lang.name.toLowerCase();
        if (!usedLangNames.includes(langName)) {
          usedLangNames.push(langName);
          this.usedLanguages.push(lang);
        }
      }
    }
  }

  // @ts-ignore
  usedPersonalitiesList(): Personality[] {
    const usedPersNames: string[] = [];
    for (let user of this.users) {
      const persName = user.personality?.name.toLowerCase();
      if (!usedPersNames.includes(<string>persName)) {
        if (persName != null) {
          usedPersNames.push(persName);
        }
        if (user.personality) {
          this.usedPersonalities.push(user.personality);
        }
      }
    }
  }

  // @ts-ignore
  usedColorPersonalitiesList(): ColorPersonality[] {
    const usedColorPersonalityNames: string[] = [];
    for (let user of this.users) {
      const colorPersonalityName = user.personality?.name.toLowerCase();
      if (!usedColorPersonalityNames.includes(<string>colorPersonalityName)) {
        if (colorPersonalityName != null) {
          usedColorPersonalityNames.push(colorPersonalityName);
        }
        if (user.colorPersonality) {
          this.usedColorPersonalities.push(user.colorPersonality);
        }
      }
    }
  }

  toggleTechSelection(tech: Technology) {
    const techName = tech.name;
    const techIndex = this.selectedTechnologies.indexOf(techName);
    if (techIndex === -1) {
      this.selectedTechnologies.push(techName);
    } else {
      this.selectedTechnologies.splice(techIndex, 1);
    }
    this.allFilters();
  }

  isSelectedTech(tech: Technology): boolean {
    return this.selectedTechnologies.includes(tech.name);
  }

  toggleLangSelection(lang: Language) {
    const langName = lang.name;
    const langIndex = this.selectedLanguages.indexOf(langName);
    if (langIndex === -1) {
      this.selectedLanguages.push(langName);
    } else {
      this.selectedLanguages.splice(langIndex, 1);
    }
    this.allFilters();
  }

  isSelectedLang(lang: Language): boolean {
    return this.selectedLanguages.includes(lang.name);
  }

  addToPersonalitiesList(pers: string) {
    if (!this.selectedPersonalities.includes(pers)) {
      this.selectedPersonalities.push(pers);
    } else {
      const index = this.selectedPersonalities.indexOf(pers);
      if (index > -1) {
        this.selectedPersonalities.splice(index, 1);
      }
    }
    this.allFilters();
  }

  addToColorPersonalitiesList(colorPersonality: string) {
    if (!this.selectedColorPersonalities.includes(colorPersonality)) {
      this.selectedColorPersonalities.push(colorPersonality);
    } else {
      const index = this.selectedColorPersonalities.indexOf(colorPersonality);
      if (index > -1) {
        this.selectedColorPersonalities.splice(index, 1);
      }
    }
    this.allFilters();
  }

  addToAgeList(event: any, age: string) {
    if (event.target.checked) {
      if (!this.selectedAges.includes(age)) {
        this.selectedAges.push(age);
      }
    } else {
      const index = this.selectedAges.indexOf(age);
      if (index > -1) {
        this.selectedAges.splice(index, 1);
      }
    }
    this.allFilters();
  }

  onWorkStatusChange() {
    this.allFilters();
  }

  allFilters() {
    let filteredUsers = [...this.verifiedAndUsersOnly];

    if (this.selectedTechnologies.length > 0) {
      filteredUsers = this.technologiesFilter(filteredUsers, this.selectedTechnologies);
    } else {
      this.actualTechnologyValue = [];
      this.restTechnologiesFilter = [];
    }

    if (this.selectedLanguages.length > 0) {
      // @ts-ignore
      filteredUsers = this.languagesFilter(filteredUsers, this.selectedLanguages);
    } else {
      this.actualLanguageValue = [];
      this.restLanguageFilter = [];
    }

    if (this.selectedPersonalities.length > 0) {
      filteredUsers = this.personalitiesFilter(filteredUsers, this.selectedPersonalities);
    } else {
      this.actualPersonalityValue = [];
      this.restPersonalityFilter = [];
    }

    if (this.selectedColorPersonalities.length > 0) {
      filteredUsers = this.colorPersonalitiesFilter(filteredUsers, this.selectedColorPersonalities);
    } else {
      this.actualColorPersonalityValue = [];
      this.restColorPersonalityFilter = [];
    }

    //filteredUsers = this.personalityFilter(filteredUsers);
    filteredUsers = this.openToWorkFilter(filteredUsers);
    if (this.selectedAges.length > 0) {
      filteredUsers = this.ageFilter(filteredUsers);
    }

    for (let user of this.nonFilteredUsers) {
      user.outOfFilters = [];
      if (this.restTechnologiesFilter.includes(user)) {
        for (let tech of this.actualTechnologyValue) {
          if (!user.technologies?.some((userTech) =>
            userTech.name.toLowerCase() === tech.toLowerCase())) {
            user.outOfFilters.push(tech);
          }
        }
      }

      if (this.restLanguageFilter.includes(user)) {
        for (let lang of this.actualLanguageValue) {
          if (!user.languages?.some((userLang) =>
            userLang.name.toLowerCase() === lang.toLowerCase())) {
            user.outOfFilters.push(lang);
          }
        }
      }

      if (this.restPersonalityFilter.includes(user)) {
        for (let pers of this.actualPersonalityValue) {
          if (user.personality?.name.toLowerCase() !== pers.toLowerCase()) {
            user.outOfFilters.push(pers);
          }
        }
      }

      if (this.restColorPersonalityFilter.includes(user)) {
        for (let colorPersonality of this.actualColorPersonalityValue) {
          if (user.colorPersonality?.name.toLowerCase() !== colorPersonality.toLowerCase()) {
            user.outOfFilters.push(colorPersonality);
          }
        }
      }

      if (this.selectedAges.length === 0) {
        this.restAgeFilter = [];
      }
      if (this.restAgeFilter.includes(user)) {
        user.outOfFilters.push(this.actualAgeValue);
      }

      if (this.actualWorkStatusValue !== "all") {
        if (this.restOpenToWorkFilter.includes(user)) {
          user.outOfFilters.push(this.actualWorkStatusValue);
        }
      }

    }
    this.users = filteredUsers;
  }

  // @ts-ignore
  technologiesFilter(users, keys: string[]) {
    const lowerCaseKeys = keys.map(key => key.toLowerCase());
    this.restTechnologiesFilter = [];

    // @ts-ignore
    const actualFilteredUsers = users.filter(user =>
      lowerCaseKeys.every(key =>
        // @ts-ignore
        user.technologies?.some(technology => technology.name.toLowerCase() === key)
      )
    );

    this.actualTechnologyValue = keys;
    this.restTechnologiesFilter = this.verifiedAndUsersOnly
      .filter(user => !actualFilteredUsers.includes(user));
    return actualFilteredUsers;
  }

  // @ts-ignore
  languagesFilter(users: User[], keys: string[]): User[] {
    const lowerCaseKeys = keys.map(key => key.toLowerCase());
    this.restLanguageFilter = [];


    // @ts-ignore
    const actualFilteredUsers = users.filter(user =>
      lowerCaseKeys.every(key =>
        // @ts-ignore
        user.languages?.some(language => language.name.toLowerCase() === key)
      )
    );

    this.actualLanguageValue = keys;
    this.restLanguageFilter = this.verifiedAndUsersOnly
      .filter(user => !actualFilteredUsers.includes(user));
    return actualFilteredUsers;
  }

  personalitiesFilter(users: User[], keys: string[]): User[] {
    const lowerCaseKeys = keys.map(key => key.toLowerCase());
    this.restPersonalityFilter = [];

    const actualFilteredUsers = users.filter(user =>
      lowerCaseKeys.every(key =>
        user.personality?.name.toLowerCase() === key
      )
    );

    this.actualPersonalityValue = keys;
    this.restPersonalityFilter = this.verifiedAndUsersOnly
      .filter(user => !actualFilteredUsers.includes(user));
    return actualFilteredUsers;
  }

  colorPersonalitiesFilter(users: User[], keys: string[]): User[] {
    const lowerCaseKeys = keys.map(key => key.toLowerCase());
    this.restColorPersonalityFilter = [];

    const actualFilteredUsers = users.filter(user =>
      lowerCaseKeys.every(key =>
        user.colorPersonality?.name.toLowerCase() === key
      )
    );

    this.actualColorPersonalityValue = keys;
    this.restColorPersonalityFilter = this.verifiedAndUsersOnly
      .filter(user => !actualFilteredUsers.includes(user));
    return actualFilteredUsers;
  }

  openToWorkFilter(users: User[]) {
    const actualFilteredUsers: User[] = [];
    this.restOpenToWorkFilter = [];

    for (let user of users) {
      if (this.filterWorkStatus === "open" && user.workStatus === true) {
        actualFilteredUsers.push(user);
      }
      if (this.filterWorkStatus === "closed" && user.workStatus === false) {
        actualFilteredUsers.push(user);
      }
      if (this.filterWorkStatus === "all") {
        actualFilteredUsers.push(user)
      }
    }
    if (this.filterWorkStatus === "open") {
      this.actualWorkStatusValue = "true"
    } else if (this.filterWorkStatus === "closed") {
      this.actualWorkStatusValue = "false"
    } else {
      this.actualWorkStatusValue = "all"
    }
    this.restOpenToWorkFilter = this.verifiedAndUsersOnly
      .filter(user => !actualFilteredUsers.includes(user));
    return actualFilteredUsers;
  }

  ageFilter(users: User[]) {
    const currentYear = new Date().getFullYear();
    let actualFilteredUsers: User[] = [];
    this.restAgeFilter = [];

    for (let user of users) {
      // @ts-ignore
      const age = currentYear - user.yearOfBirth;
      { // @ts-ignore
        for (let ageValue of this.selectedAges) {
          if (ageValue === "18" && (age >= 18 && age <= 25)) {
            if (!actualFilteredUsers.includes(user)) {
              actualFilteredUsers.push(user)
            }
          }
          if (ageValue === "25" && (age >= 25 && age <= 30)) {
            if (!actualFilteredUsers.includes(user)) {
              actualFilteredUsers.push(user)
            }
          }
          if (ageValue === "30" && (age >= 30 && age <= 35)) {
            if (!actualFilteredUsers.includes(user)) {
              actualFilteredUsers.push(user)
            }
          }
          if (ageValue === "35" && (age >= 35 && age <= 40)) {
            if (!actualFilteredUsers.includes(user)) {
              actualFilteredUsers.push(user)
            }
          }
          if (ageValue === "40" && age >= 40) {
            if (!actualFilteredUsers.includes(user)) {
              actualFilteredUsers.push(user)
            }
          }
        }
      }
    }

    this.actualAgeValue = "age"
    this.restAgeFilter = this.verifiedAndUsersOnly
      .filter(user => !actualFilteredUsers.includes(user));
    return actualFilteredUsers;
  }

  clearAllFilters() {
    this.selectedTechnologies = [];
    this.selectedLanguages = [];
    this.selectedAges = [];
    this.filterWorkStatus = 'all';
    this.selectedPersonalities = [];
    this.selectedColorPersonalities = [];
    this.selectAllPersonalities = true;

    for (let user of this.nonFilteredUsers) {
      user.outOfFilters = [];
    }

    const ageCheckboxes = document.querySelectorAll<HTMLInputElement>('input[type="checkbox"]');
    const ageAllCheckbox = document.querySelector<HTMLInputElement>('input[type="checkbox"][value="all"]');
    ageCheckboxes.forEach((checkbox) => {
      checkbox.checked = false;
    });

    if (ageAllCheckbox) {
      ageAllCheckbox.checked = true;
    }
    this.allFilters();
  }
}
