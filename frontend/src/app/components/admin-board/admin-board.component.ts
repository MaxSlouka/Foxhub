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
  // @ts-ignore
  selectedUser: User;

  constructor(private apiService:ApiService) {
  }

  ngOnInit(): void {
    this.apiService.getAll().subscribe(users => {
      this.users=users;
      this.selectedUser = users[0];
    });
  }



  deleteUser(user: User) {
    console.log(user.firstName)
  }

  setSelectedUser(user: User) {
    this.selectedUser = user;
  }

}
