import {Component} from '@angular/core';
import {User} from "./models/user";
import {ApiService} from "./_services/api/api.service"

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';
  // @ts-ignore
  users: User[];

  constructor(private apiService: ApiService) {
  }

  private getUsers(): void {
    this.apiService
      .getAll().subscribe((users) =>
      this.users = users
    );
  }
}
