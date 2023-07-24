import {Component, OnInit} from '@angular/core';
import {DataService} from "../../_services/api/data.service";
import {Technology} from "../../models/technology";
import {TechnologyService} from "../../_services/technology.service";
import {ApiService} from "../../_services/api/api.service";
import {User} from "../../models/user";


@Component({
  selector: 'app-people-page',
  templateUrl: './people-page.component.html',
  styleUrls: ['./people-page.component.css']
})

export class PeoplePageComponent implements OnInit {
  technologies: Technology[] = [];
  selectedTechnologies: string[] = [];
  usedTechnologies: Technology[] = [];


  // @ts-ignore
  users: User[] = [];
  fullUsers: User[] = [];

  constructor(
              private technolgyService: TechnologyService,
              private apiService: ApiService,
              ){
  }

  ngOnInit(): void {
    this.technolgyService.getAll().subscribe(technologies => {
      this.technologies = technologies;
      this.usedTechnologiesList();
    });
    // @ts-ignore
    this.apiService.getAll().subscribe(users => {
      this.users = users;
      this.fullUsers = users;
      this.usedTechnologiesList();
    });
  }

  // @ts-ignore
  usedTechnologiesList(): Technology[] {
    const usedTechnologies: Technology[] = [];
    for (let user of this.users) {
      // @ts-ignore
      for (let tech of user.technologies) {
        if (!usedTechnologies.some(usedTech => usedTech.name === tech.name)) {
          usedTechnologies.push(tech);
        }
      }
    }
    return usedTechnologies;
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
}
