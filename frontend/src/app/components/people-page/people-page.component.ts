import { Component } from '@angular/core';
import {PeopleService} from "../../_services/people.service";
import {User} from "../../user";

@Component({
  selector: 'app-people-page',
  templateUrl: './people-page.component.html',
  styleUrls: ['./people-page.component.css']
})
export class PeoplePageComponent {
  users: User[] = [];

  constructor(private peopleService: PeopleService) {
  }

  ngOnInit(): void {
    this.peopleService.getAll().subscribe((users) => this.users = users)
  }

}
