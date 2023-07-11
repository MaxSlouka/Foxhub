import {Component, Input} from '@angular/core';
import {User} from "../../user";

@Component({
  selector: 'app-people-page-card',
  templateUrl: './people-page-card.component.html',
  styleUrls: ['./people-page-card.component.css']
})
export class PeoplePageCardComponent {
// @ts-ignore
  @Input() user: User;


}
