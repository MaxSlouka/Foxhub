import { Component, OnInit } from '@angular/core';
import { DataService } from "../../_services/api/data.service";
import {Technology} from "../../models/technology";
import {TechnologyService} from "../../_services/technology.service";

@Component({
  selector: 'app-people-page',
  templateUrl: './people-page.component.html',
  styleUrls: ['./people-page.component.css']
})

export class PeoplePageComponent implements OnInit {
  technologies:Technology[] = [];
  selectedTechnologies:string[] = [];

  constructor(public dataService: DataService, private technolgyService:TechnologyService) {
  }

  ngOnInit(): void {
    this.technolgyService.getAll().subscribe(technologies=>{
      for (const technology of technologies) {
        this.technologies.push(technology);
      }
    });
  }

  onSomthing() {
    console.log(this.technologies);
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
    console.log(this.selectedTechnologies);
  }

}
