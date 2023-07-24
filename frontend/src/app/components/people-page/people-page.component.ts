import { Component, OnInit, AfterViewInit, ViewChild, ElementRef } from '@angular/core';
import { DataService } from "../../_services/api/data.service";
import {Technology} from "../../models/technology";
import {TechnologyService} from "../../_services/technology.service";
import {ApiService} from "../../_services/api/api.service";
import {User} from "../../models/user";
import {Language} from "../../models/language";
import {LanguageService} from "../../_services/language.service";


@Component({
  selector: 'app-people-page',
  templateUrl: './people-page.component.html',
  styleUrls: ['./people-page.component.css']
})


export class PeoplePageComponent implements OnInit, AfterViewInit {
  @ViewChild('customRange3', { static: true }) rangeInputRef!: ElementRef<HTMLInputElement>;
  @ViewChild('rangeValue', { static: true }) rangeValueRef!: ElementRef<HTMLSpanElement>;

  technologies: Technology[] = [];
  languages: Language[] = [];
  selectedTechnologies: string[] = [];
  selectedLanguages: string[] = [];
  usedTechnologies: Technology[] = [];
  usedLanguages: Language[] = [];


  // @ts-ignore
  users: User[] = [];
  fullUsers: User[] = [];

  filterContentExpanded: boolean = true

  constructor(public dataService: DataService,
              private technologyService: TechnologyService,
              private languageService: LanguageService,
              private apiService: ApiService) {
  }

  ngOnInit(): void {

    this.technologyService.getAll().subscribe(technologies => {
      this.technologies = technologies;

    });
    this.languageService.getAll().subscribe(languages => {
      this.languages = languages;
    })
    // @ts-ignore
    this.apiService.getAll().subscribe(users => {
      this.users = users;
      this.fullUsers = users;
      this.usedTechnologiesList();
      this.usedLanguagesList();
    });
  }

  ngAfterViewInit(): void {
    const rangeInput = this.rangeInputRef.nativeElement;
    const rangeValue = this.rangeValueRef.nativeElement;

    rangeInput.addEventListener('input', (event) => {
      const target = event.target as HTMLInputElement;
      rangeValue.textContent = target.value;

    });
  }

  toggleFilterContent(): void {
    this.filterContentExpanded = !this.filterContentExpanded;
  }

  // @ts-ignore
  usedTechnologiesList(): Technology[] {
    for (let user of this.users) {
      // @ts-ignore
      for (let tech of user.technologies) {
        // @ts-ignore
        if (!this.usedTechnologies.includes(tech)) {
          // @ts-ignore
          this.usedTechnologies.push(tech);
        }
      }
    }
  }


  // @ts-ignore
  usedLanguagesList(): Language[] {
    for (let user of this.users) {
      // @ts-ignore
      for (let lang of user.languages) {
        if (!this.usedLanguages.includes(lang)) {
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
    this.technologiesFilter(this.selectedTechnologies);
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
    this.languagesFilter(this.selectedLanguages);
  }


  technologiesFilter(keys: string[]) {
    if (keys.length === 0) {
      this.users = this.fullUsers;
      return;
    }

    const lowerCaseKeys = keys.map(key => key.toLowerCase());
    this.users = this.fullUsers.filter(user =>
      lowerCaseKeys.every(key =>
          user.technologies && user.technologies.some(technology =>
            technology.name.toLowerCase().includes(key)
          )
      )
    );
  }

  languagesFilter(keys: string[]) {
    if (keys.length === 0) {
      this.users = this.fullUsers;
      return;
    }

    const lowerCaseKeys = keys.map(key => key.toLowerCase());
    this.users = this.fullUsers.filter(user =>
      lowerCaseKeys.every(key =>
          user.languages && user.languages.some(language =>
            language.name.toLowerCase().includes(key)
          )
      )
    );
  }
}
