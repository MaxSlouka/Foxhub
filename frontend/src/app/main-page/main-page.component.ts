import { Component } from '@angular/core';
import {User} from "../user";

interface onInit {
}

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements onInit {

  protected readonly User = User;
}
