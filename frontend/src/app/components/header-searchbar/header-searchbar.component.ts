import {Component, Input, EventEmitter, Output} from '@angular/core';
import {User} from "../../models/user";


@Component({
  selector: 'app-header-searchbar',
  templateUrl: './header-searchbar.component.html',
  styleUrls: ['./header-searchbar.component.css']
})
export class HeaderSearchbarComponent {
  // @ts-ignore
  @Input() users: User[];
  // @ts-ignore
  filtered: User[];

  @Output() dataEvent = new EventEmitter<any>();


  public searchUser(key: string): void {
    let results: User[] = [];
    for (const user of this.users) {
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

    if (!key) {
      this.filtered = [];
    }
    this.dataEvent.emit(this.filtered)
  }
}
