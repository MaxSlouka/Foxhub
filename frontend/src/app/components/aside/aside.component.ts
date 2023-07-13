import { Component } from '@angular/core';
import {User} from "../../models/user";

@Component({
  selector: 'app-aside',
  templateUrl: './aside.component.html',
  styleUrls: ['./aside.component.css']
})
export class AsideComponent {
  user: User = new User();
}
