import {Component, OnInit} from '@angular/core';
import {User} from "../../models/user";
import {ApiService} from "../../_services/api/api.service";

@Component({
  selector: 'app-admin-board',
  templateUrl: './admin-board.component.html',
  styleUrls: ['./admin-board.component.css']
})
export class AdminBoardComponent implements OnInit{
  // @ts-ignore
  users: User[] = [];
  selectedUser: User = {password: "", firstName: "", lastName: "", email: ""};

  constructor(private apiService:ApiService) {
  }

  ngOnInit(): void {
    this.apiService.getAll().subscribe(users => {
      this.users=users;
      this.selectedUser = users[0];
    });
  }

  deleteUser() {
    console.log(this.selectedUser.firstName) // todo remove
    // this.apiService.removeUser(this.selectedUser.nickname)
  }

  setSelectedUser(user: User) {
    console.log("hi")
    this.selectedUser = user;
    console.log(this.selectedUser);
  }

  sayHi() {
    console.log("hi")
  }
}
