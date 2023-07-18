import {Component, Input, EventEmitter, Output} from '@angular/core';
import {User} from "../../models/user";
import {ApiService} from "../../_services/api/api.service";


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

  constructor(private apiService: ApiService) {
  }

  public searchUser(key: string): void {
    let results: User[] = [];
    for (const user of this.users) {
      this.apiService.search(user,key,results)
    }
    this.filtered = results;
    if (!key) {
      this.filtered = [];
    }
    this.dataEvent.emit(this.filtered)
  }
}
