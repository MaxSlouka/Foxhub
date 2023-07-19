import { Component, Input } from '@angular/core';
import { User } from "../../models/user";

@Component({
  selector: 'app-header-searchbar-card',
  templateUrl: './header-searchbar-card.component.html',
  styleUrls: ['./header-searchbar-card.component.css']
})

export class HeaderSearchbarCardComponent {

  // @ts-ignore
  @Input() user: User;
}
