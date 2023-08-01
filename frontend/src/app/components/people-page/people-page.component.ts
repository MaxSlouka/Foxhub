import {Component, OnInit, ViewChild, ElementRef} from '@angular/core';
import {Technology} from "../../models/technology";
import {TechnologyService} from "../../_services/technology.service";
import {ApiService} from "../../_services/api/api.service";
import {User} from "../../models/user";
import {Language} from "../../models/language";
import {LanguageService} from "../../_services/language.service";
import {Personality} from "../../models/personality";
import {PersonalityService} from "../../_services/personality.service";

@Component({
  selector: 'app-people-page',
  templateUrl: './people-page.component.html',
  styleUrls: ['./people-page.component.css']
})

export class PeoplePageComponent implements OnInit {
  @ViewChild('customRange3', {static: true}) rangeInputRef!: ElementRef<HTMLInputElement>;
  @ViewChild('rangeValue', {static: true}) rangeValueRef!: ElementRef<HTMLSpanElement>;

  technologies: Technology[] = [];
  languages: Language[] = [];
  selectedTechnologies: string[] = [];
  selectedLanguages: string[] = [];
  selectedAges: string[] = [];
  usedTechnologies: Technology[] = [];
  usedLanguages: Language[] = [];

  // @ts-ignore
  users: User[] = [];
  verifiedAndUsersOnly: User[] = [];
  nonFilteredUsers: User[] = [];

  filterContentExpanded: boolean = true;

  // @ts-ignore
  filterWorkStatus: string;

  personalities: Personality[] = [];
  selectedPersonality: Personality | undefined;
  selectAllPersonalities: boolean = true;
  addedUsers: User[] = [];


  actualPersonalityValue: string = '';
  actualLanguageValue: string = '';
  actualTechnologyValue: string = '';
  actualAgeValue: string = '';
  actualWorkStatusValue: string = '';

  restPersonalityFilter: User[] = [];
  restOpenToWorkFilter: User[] = [];
  restAgeFilter: User[] = [];
  restLanguageFilter: User[] = [];
  restTechnologiesFilter: User[] = [];


  constructor(private technologyService: TechnologyService,
              private languageService: LanguageService,
              private apiService: ApiService,
              private personalityService: PersonalityService) {
  }

  ngOnInit(): void {
    this.filterWorkStatus = 'all';

    this.technologyService.getAll().subscribe(technologies => {
      this.technologies = technologies;
    });
    this.languageService.getAll().subscribe(languages => {
      this.languages = languages;
    });
    this.personalityService.getAll().subscribe(personalities => {
      this.personalities = personalities
    });

    // @ts-ignore
    this.apiService.getAll().subscribe(users => {
      this.users = users.filter((user: User) => user.verified && user.role?.name === "USER");
      this.verifiedAndUsersOnly = users.filter((user: User) => user.verified && user.role?.name === "USER");
      this.nonFilteredUsers = this.verifiedAndUsersOnly;
      this.usedTechnologiesList();
      this.usedLanguagesList();
      for (let user of this.users) {
        if (this.addedUsers.includes(user)) {
          user.inCart = true;
        }
      }
    });
  }

  get filteredUsers(): any[] {
    // @ts-ignore
    return this.nonFilteredUsers.filter(user => user.outOfFilters?.length > 2);
  }

  onPersonalitySelect(event: any) {
    const selectedPersonalityId = +event.target.value;
    this.selectAllPersonalities = false
    this.selectedPersonality = this.personalities.find(p =>
      p.id === selectedPersonalityId
    );
    if (event.target.value === "all") {
      this.selectAllPersonalities = true;
    }
    this.allFilters();
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

  addToTechList(event: any, tech: string) {
    if (event.target.checked) {
      if (!this.selectedTechnologies.includes(tech)) {
        this.selectedTechnologies.push(tech);
      }
    } else {
      const index = this.selectedTechnologies.indexOf(tech);
      if (index > -1) {
        this.selectedTechnologies.splice(index, 1);
      }
    }
    this.allFilters()
  }

  addToLangList(event: any, lang: string) {
    if (event.target.checked) {
      if (!this.selectedLanguages.includes(lang)) {
        this.selectedLanguages.push(lang);
      }
    } else {
      const index = this.selectedLanguages.indexOf(lang);
      if (index > -1) {
        this.selectedLanguages.splice(index, 1);
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
      this.actualTechnologyValue = '';
      this.restTechnologiesFilter = [];
    }

    if (this.selectedLanguages.length > 0) {
      filteredUsers = this.languagesFilter(filteredUsers, this.selectedLanguages);
    } else {
      this.actualLanguageValue = '';
      this.restLanguageFilter = [];
    }

    filteredUsers = this.ageFilter(filteredUsers);
    filteredUsers = this.openToWorkFilter(filteredUsers);
    filteredUsers = this.personalityFilter(filteredUsers);

    for (let user of this.nonFilteredUsers) {

      user.outOfFilters = [];

      if (this.restTechnologiesFilter.includes(user)) {
        user.outOfFilters.push(this.actualTechnologyValue);
      }

      if (this.restLanguageFilter.includes(user)) {
        user.outOfFilters.push(this.actualLanguageValue);
      }

      if (this.restAgeFilter.includes(user)) {
        user.outOfFilters.push(this.actualAgeValue);
      }

      if (this.actualWorkStatusValue !== "all") {
        if (this.restOpenToWorkFilter.includes(user)) {
          user.outOfFilters.push(this.actualWorkStatusValue);
        }
      }

      if (this.restPersonalityFilter.includes(user)) {
        user.outOfFilters.push(this.actualPersonalityValue);

      }
    }
    console.log(this.nonFilteredUsers);
    this.users = filteredUsers;
  }

  // @ts-ignore
  technologiesFilter(users, keys: string[]) {

    const lowerCaseKeys = keys.map(key => key.toLowerCase());
    const actualFilteredUsers: User[] = [];
    this.restTechnologiesFilter = [];

    for (let user of users) {
      // @ts-ignore
      if (user.technologies.some(technology => lowerCaseKeys
        .includes(technology.name.toLowerCase()))) {
        actualFilteredUsers.push(user);
      }
    }

    this.actualTechnologyValue = lowerCaseKeys.join(' ').toString();
    this.restTechnologiesFilter = this.verifiedAndUsersOnly
      .filter(user => !actualFilteredUsers.includes(user));
    return actualFilteredUsers;
  }

  // @ts-ignore
  languagesFilter(users: User[], keys: string[]): User[] {
    const lowerCaseKeys = keys.map(key => key.toLowerCase());
    const actualFilteredUsers: User[] = [];
    this.restLanguageFilter = [];

    for (let user of users) {

      // @ts-ignore
      if (user.languages.some(language => lowerCaseKeys
        .includes(language.name.toLowerCase()))) {
        actualFilteredUsers.push(user);
      }
    }

    this.actualLanguageValue = lowerCaseKeys.join(' ').toString();
    this.restLanguageFilter = this.verifiedAndUsersOnly
      .filter(user => !actualFilteredUsers.includes(user));
    return actualFilteredUsers;
  }

  personalityFilter(users: User[]) {
    const actualFilteredUsers: User[] = [];
    this.restPersonalityFilter = [];

    for (let user of users) {
      // @ts-ignore
      if (user.personality?.id === this.selectedPersonality?.id) {
        actualFilteredUsers.push(user);
      } else if (this.selectAllPersonalities) {
        actualFilteredUsers.push(user);
      }
    }

    if (this.selectAllPersonalities === undefined) {
      this.actualPersonalityValue = "";
    } else {
      this.actualPersonalityValue = <string>this.selectedPersonality?.name;
    }

    this.restPersonalityFilter = this.verifiedAndUsersOnly
      .filter(user => !actualFilteredUsers.includes(user));
    return actualFilteredUsers;
  }

  openToWorkFilter(users: User[]) {
    const actualFilteredUsers: User[] = [];
    this.restOpenToWorkFilter = [];

    for (let user of users) {
      if ((this.filterWorkStatus === "open" && user.workStatus === true) ||
        (this.filterWorkStatus === "closed" && user.workStatus === false ||
          this.filterWorkStatus === "all")) {
        actualFilteredUsers.push(user);
      }
    }

    if (this.filterWorkStatus === "open") {
      this.actualWorkStatusValue = "Seeking employment: true"
    } else if (this.filterWorkStatus === "closed") {
      this.actualWorkStatusValue = "Seeking employment: false"
    } else {
      this.actualWorkStatusValue = "all"
    }
    this.restOpenToWorkFilter = this.verifiedAndUsersOnly
      .filter(user => !actualFilteredUsers.includes(user));
    return actualFilteredUsers;
  }

  ageFilter(users: User[]) {
    const currentYear = new Date().getFullYear();
    const actualFilteredUsers: User[] = [];
    this.restAgeFilter = [];

    for (let user of users) {
      // @ts-ignore
      const age = currentYear - user.yearOfBirth;
      { // @ts-ignore
        for (let ageValue: string of this.selectedAges) {
          if (ageValue === "all") {
            if (!actualFilteredUsers.includes(user)) {
              actualFilteredUsers.push(user)
            }
          }
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
          if (ageValue === "40" && age >= 40 ) {
            if (!actualFilteredUsers.includes(user)) {
              actualFilteredUsers.push(user)
            }
          }
        }
      }
    }

    this.actualAgeValue = this.selectedAges.join(" ").toString();
    this.restAgeFilter = this.verifiedAndUsersOnly
      .filter(user => !actualFilteredUsers.includes(user));
    return actualFilteredUsers;
  }
}


