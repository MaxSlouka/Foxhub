import { Component, OnInit } from '@angular/core';
import { AuthService } from "../../_services/auth.service";
import { StorageService } from "../../_services/storage.service";
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-intro',
  templateUrl: './intro.component.html',
  styleUrls: ['./intro.component.css']
})
export class IntroComponent implements OnInit {

  isLoggedIn = false;
  public showCookiePopup = false;

  constructor(private authService: AuthService,
    private storageService: StorageService,
    private cookieService: CookieService) {
  }

  ngOnInit(): void {
    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
      // this.roles = this.storageService.getUser().roles;
    }
    this.showCookiePopup = this.cookieService.get('cookie_consent') !== 'true';
  }

  acceptCookies() {
    this.cookieService.set('cookie_consent', 'true');
    this.showCookiePopup = false;
  }

  declineCookies() {
    this.cookieService.delete('cookie_consent');
    this.showCookiePopup = false;
  }
}
