import {Component, OnInit, AfterViewInit, ViewChild, ElementRef} from '@angular/core';
import {Technology} from "../../models/technology";
import {TechnologyService} from "../../_services/technology.service";
import {ApiService} from "../../_services/api/api.service";
import {User} from "../../models/user";
import {Language} from "../../models/language";
import {LanguageService} from "../../_services/language.service";
import {Personality} from "../../models/personality";
import {PersonalityService} from "../../_services/personality.service";
import {CartService} from "../../_services/cart.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-people-page',
  templateUrl: './people-page.component.html',
  styleUrls: ['./people-page.component.css']
})

export class PeoplePageComponent implements OnInit, AfterViewInit {
  @ViewChild('customRange3', {static: true}) rangeInputRef!: ElementRef<HTMLInputElement>;
  @ViewChild('rangeValue', {static: true}) rangeValueRef!: ElementRef<HTMLSpanElement>;

  technologies: Technology[] = [];
  languages: Language[] = [];
  selectedTechnologies: string[] = [];
  selectedLanguages: string[] = [];
  usedTechnologies: Technology[] = [];
  usedLanguages: Language[] = [];
  // @ts-ignore
  users: User[] = [];
  fullUsers: User[] = [];
  verifiedAndUsersOnly: User[] = [];
  filterContentExpanded: boolean = true;
  isRangeChanged: boolean = false;
  // @ts-ignore
  workStatus: string;
  personalities: Personality[] = [];
  selectedPersonality: Personality | undefined;
  selectAllPersonalities: boolean = true;
  addedUsers: User[] = [];

  // @ts-ignore
  private cartItemsSubscription: Subscription;

  constructor(private technologyService: TechnologyService,
              private languageService: LanguageService,
              private apiService: ApiService,
              private personalityService: PersonalityService,
              private cartService: CartService) {
  }

  ngOnInit(): void {
    this.workStatus = 'open';

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
      this.usedTechnologiesList();
      this.usedLanguagesList();
    });
    this.cartItemsSubscription = this.cartService.getCartItemsObservable()
      .subscribe((cartItems: User[]) => {
        this.addedUsers = cartItems;
      });
  }

  ngAfterViewInit(): void {
    const rangeInput = this.rangeInputRef.nativeElement;
    const rangeValue = this.rangeValueRef.nativeElement;

    rangeInput.addEventListener('input', (event) => {
      const target = event.target as HTMLInputElement;
      rangeValue.textContent = target.value;
      this.isRangeChanged = true;
      this.allFilters();
    });
  }

  onPersonalitySelect(event: any) {
    const selectedPersonalityId = +event.target.value;
    this.selectAllPersonalities = false
    this.selectedPersonality = this.personalities.find(p =>
      p.id === selectedPersonalityId
    );
    if (event.target.value === "All") {
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

  addToLangList(event: any, tech: string) {
    if (event.target.checked) {
      if (!this.selectedLanguages.includes(tech)) {
        this.selectedLanguages.push(tech);
      }
    } else {
      const index = this.selectedLanguages.indexOf(tech);
      if (index > -1) {
        this.selectedLanguages.splice(index, 1);
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
    }

    if (this.selectedLanguages.length > 0) {
      filteredUsers = this.languagesFilter(filteredUsers, this.selectedLanguages);
    }

    // @ts-ignore
    if (this.isRangeChanged) {
      filteredUsers = this.ageFilter(filteredUsers);
    }

    filteredUsers = this.openToWorkFilter(filteredUsers);
    filteredUsers = this.personalityFilter(filteredUsers);

    this.users = filteredUsers;
  }

  // @ts-ignore
  technologiesFilter(users, keys: string[]) {
    const lowerCaseKeys = keys.map(key => key.toLowerCase());

    // @ts-ignore
    return users.filter(user =>
      lowerCaseKeys.every(key =>
          // @ts-ignore
          user.technologies && user.technologies.some(technology =>
            technology.name.toLowerCase().includes(key)
          )
      )
    );
  }

  // @ts-ignore
  languagesFilter(users, keys: string[]) {
    const lowerCaseKeys = keys.map(key => key.toLowerCase());
    // @ts-ignore
    return users.filter(user =>
      lowerCaseKeys.every(key =>
          // @ts-ignore
          user.languages && user.languages.some(language =>
            language.name.toLowerCase().includes(key)
          )
      )
    );
  }

  ageFilter(filteredUsers: User[]) {
    const currentYear = new Date().getFullYear();
    const actualFilteredUsers: User[] = [];
    for (let user of filteredUsers) {
      // @ts-ignore
      const age = currentYear - user.yearOfBirth;
      { // @ts-ignore
        if (age <= this.rangeInputRef.nativeElement.value) {
          actualFilteredUsers.push(user);
        }
      }
    }
    return actualFilteredUsers;
  }

  // @ts-ignore
  openToWorkFilter(filteredUsers) {
    const actualFilteredUsers: User[] = [];
    for (let user of filteredUsers) {
      // @ts-ignore
      if (this.workStatus === "open" && user.workStatus === true) {
        actualFilteredUsers.push(user);
      }
      // @ts-ignore
      if (this.workStatus === "closed" && user.workStatus === false) {
        actualFilteredUsers.push(user);
      }
    }
    return actualFilteredUsers;
  }

  personalityFilter(filteredUsers: User[]) {
    const actualFilteredUsers: User[] = [];
    for (let user of filteredUsers) {
      // @ts-ignore
      if (user.personality?.id === this.selectedPersonality?.id) {
        actualFilteredUsers.push(user);
      } else if (this.selectAllPersonalities) {
        actualFilteredUsers.push(user);
      }
    }
    return actualFilteredUsers;
  }
}


