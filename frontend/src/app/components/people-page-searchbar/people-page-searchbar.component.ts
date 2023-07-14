import {Component, Input} from '@angular/core';
import {User} from "../../models/user";
import {Technology} from "../../models/technology";


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

  public searchUser(key: string): void {
    let results: User[] = [];
    for (const user of this.users) {

      // @ts-ignore
      for(const technology of user.technologies){
        if(technology.name.toLowerCase().indexOf(key.toLowerCase()) !== -1){
          results.push(user);
        }
      }
      if (user.firstName.toLowerCase().indexOf(key.toLowerCase()) !== -1
        || user.lastName.toLowerCase().indexOf(key.toLowerCase()) !== -1
      ) {
        results.push(user);
      }
    }
    this.users = results;
    if (!key) {
      this.users = this.fullUsers
    }
  }
}
