import {Component, OnInit} from '@angular/core';
import {User} from "../../models/user";
import {ApiService} from "../../_services/api/api.service";

@Component({
  selector: 'app-aside',
  templateUrl: './aside.component.html',
  styleUrls: ['./aside.component.css']
})

export class AsideComponent implements OnInit {

  users: User[] = [];
  verifiedUsers: User[] = [];
  showPeople = false;
  rotationClass = '';

  constructor(private apiService: ApiService) {
  }

  togglePeople(): void {
    this.showPeople = !this.showPeople;
    this.rotationClass = this.showPeople ? 'rotate-up' : 'rotate-down';
  }

  ngOnInit() {
    // Fetch all users from the API
    this.apiService.getAll().subscribe((usersFetch: User[]) => {
      this.users = usersFetch;

      this.verifiedUsers = this.users.filter(user => user.verified);
    });
  }
}
