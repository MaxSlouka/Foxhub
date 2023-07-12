import {Component, Input} from '@angular/core';
import {Technology} from "../../models/Technology";

@Component({
  selector: 'app-technologies',
  templateUrl: './technologies.component.html',
  styleUrls: ['./technologies.component.css']
})
export class TechnologiesComponent {
  // @ts-ignore
 @Input() technology :Technology;
}
