import {Component, Input} from '@angular/core';
import {User} from "../../models/user";

@Component({
  selector: 'app-people-page-searchbar',
  templateUrl: './people-page-searchbar.component.html',
  styleUrls: ['./people-page-searchbar.component.css']
})
export class PeoplePageSearchbarComponent {
// @ts-ignore
  @Input() users: User[];
  // @ts-ignore
  @Input() fullUsers: User[];

  // @ts-ignore
  filtered: User[];

  public searchUser(key: string): void {
    let results: User[] = [];
    for (const user of this.fullUsers) {
      let hasTechnologyMatch = false;

      // @ts-ignore
      for (const technology of user.technologies) {
        if (technology.name.toLowerCase().includes(key.toLowerCase())) {
          results.push(user);
          hasTechnologyMatch = true;
          break;
        }
      }

      if (!hasTechnologyMatch) {
        if (
          user.firstName.toLowerCase().includes(key.toLowerCase()) ||
          user.lastName.toLowerCase().includes(key.toLowerCase())
        ) {
          results.push(user);
        }
      }
    }

    this.filtered = results;

    if(!key){
      this.filtered = [];
    }
  }
}
