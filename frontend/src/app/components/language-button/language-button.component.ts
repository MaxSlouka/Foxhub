import { Component, Input } from '@angular/core';
import {Language} from "../../models/language";

@Component({
  selector: 'app-language-button',
  templateUrl: './language-button.component.html',
  styleUrls: ['./language-button.component.css']
})
export class LanguageButtonComponent {
// @ts-ignore
  @Input()language: Language;
}
