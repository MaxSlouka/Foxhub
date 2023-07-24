import {Component, OnInit} from '@angular/core';
import {DataService} from "../../_services/api/data.service";
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

export class PeoplePageComponent implements OnInit {
  technologies: Technology[] = [];
  languages: Language[] = [];
  selectedTechnologies: string[] = [];
  selectedLanguages: string[] = [];
  usedTechnologies: Technology[] = [];
  usedLanguages: Language[] = [];

  // @ts-ignore
  users: User[];

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
      this.usedTechnologiesList();
      this.usedLanguagesList();
    });
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
      console.log(user)
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
  }
}
