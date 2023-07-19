import { Component, Input } from '@angular/core';
import {Language} from "../../models/language";

@Component({
  selector: 'app-laguage',
  templateUrl: './laguage.component.html',
  styleUrls: ['./laguage.component.css']
})
export class LaguageComponent {
// @ts-ignore
  @Input() language: Language;
}
