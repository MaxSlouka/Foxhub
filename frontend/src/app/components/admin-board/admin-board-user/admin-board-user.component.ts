import {Component, Input} from '@angular/core';
import {User} from "../../../models/user";

@Component({
  selector: 'app-admin-board-user',
  templateUrl: './admin-board-user.component.html',
  styleUrls: ['./admin-board-user.component.css']
})
export class AdminBoardUserComponent {

  // @ts-ignore
  @Input() user: User;

  sayHi(){
    console.log("hi")
  }

}
