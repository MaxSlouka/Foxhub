import { Component, Input } from '@angular/core';
import {SocialMedia} from "../../models/socialMedia";

@Component({
  selector: 'app-social-media',
  templateUrl: './social-media.component.html',
  styleUrls: ['./social-media.component.css']
})
export class SocialMediaComponent {
// @ts-ignore
  @Input() social: SocialMedia;
}
