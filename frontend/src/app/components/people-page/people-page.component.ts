import {Component, OnInit, AfterContentInit} from '@angular/core';
import {PeopleService} from "../../_services/people.service";
import {User} from "../../models/user";
import {Observable} from "rxjs";

@Component({
  selector: 'app-people-page',
  templateUrl: './people-page.component.html',
  styleUrls: ['./people-page.component.css']
})
export class PeoplePageComponent implements OnInit {
  users: User[] = [];

  constructor(private peopleService: PeopleService) {
  }

  ngOnInit(): void {
    this.getUsers();
  }

  private getUsers(): void {
    this.peopleService
      .getAll().subscribe((users) =>
      this.users = users
    );
  }

  protected readonly Observable = Observable;

}
