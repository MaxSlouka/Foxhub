import {Component} from '@angular/core';
import {User} from "./models/user";
import {PeopleService} from "./_services/people.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';

}
