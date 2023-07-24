import {Component, OnInit} from '@angular/core';
import {DataService} from "../../_services/api/data.service";
import {Technology} from "../../models/technology";
import {User} from "../../models/user";

@Component({
  selector: 'app-people-page',
  templateUrl: './people-page.component.html',
  styleUrls: ['./people-page.component.css']
})

export class PeoplePageComponent implements OnInit {

  usedTechnologies: Technology[] = [];
  // @ts-ignore
  users: User[] ;

  constructor(public dataService: DataService) {
  }

  ngOnInit(): void {
   if(this.dataService.users){
     this.users = this.dataService.users;
     usedTechnologiesList();
     console.log(this.users);
   }
  }
}


// @ts-ignore
function usedTechnologiesList(): Technology[] {
  // @ts-ignore
  for(let user: User of this.users) {
   // @ts-ignore
    for(let technology: Technology of user.technologies) {
     // @ts-ignore
     if(!this.usedTechnologies.includes(technology)) {
       // @ts-ignore
       this.usedTechnologies.push(technology);
     }
   }
  }
  // @ts-ignore
  console.log(this.usedTechnologies);
}
