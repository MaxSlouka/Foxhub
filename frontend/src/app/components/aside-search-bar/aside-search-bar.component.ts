import { Component, Input } from '@angular/core';
import { User } from "../../models/user";
import { ApiService } from "../../_services/api/api.service";

@Component({
  selector: 'app-aside-search-bar',
  templateUrl: './aside-search-bar.component.html',
  styleUrls: ['./aside-search-bar.component.css']
})

export class AsideSearchBarComponent {
  // @ts-ignore
  @Input users: User[];
  // @ts-ignore
  @Input fullUsers: User[];

  constructor(private apiService: ApiService) {
  }

  ngOnInit() {
  }

  public searchUser(key: string): void {
    let results: User[] = [];
    for (const user of this.fullUsers) {
      this.apiService.search(user, key, results)
    }
    this.users = results;
  }
}
