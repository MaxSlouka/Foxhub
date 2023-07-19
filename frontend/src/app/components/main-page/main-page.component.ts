import { Component } from '@angular/core';
import { User } from "../../models/user";

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})

export class MainPageComponent {

  protected readonly User: User | undefined;
}
