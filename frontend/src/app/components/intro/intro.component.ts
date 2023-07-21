import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../_services/auth.service";
import {StorageService} from "../../_services/storage.service";
import {CookieService} from 'ngx-cookie-service';

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

//
// import { Component, OnInit } from '@angular/core';
// import { AuthService } from "../../_services/auth.service";
// import { StorageService } from "../../_services/storage.service";
// import { CookieService } from 'ngx-cookie-service';
//
// @Component({
//   selector: 'app-intro',
//   templateUrl: './intro.component.html',
//   styleUrls: ['./intro.component.css']
// })
//
// export class IntroComponent implements OnInit {
//
//   isLoggedIn = false;
//   public showCookiePopup = false;
//
//   constructor(private authService: AuthService,
//               private storageService: StorageService,
//               private cookieService: CookieService) {
//   }
//
//   ngOnInit(): void {
//     if (this.storageService.isLoggedIn()) {
//       this.isLoggedIn = true;
//       // this.roles = this.storageService.getUser().roles;
//     }
//     this.showCookiePopup = !this.cookieService.check('cookie_consent');
//   }
//
//   acceptCookies() {
//     this.cookieService.set('cookie_consent', 'true');
//     this.showCookiePopup = false;
//   }
//
//   declineCookies() {
//     this.cookieService.set('cookie_consent', 'false');
//     this.showCookiePopup = false;
//   }
// }

// cookie ask only once


// for testing cookie:

//     Google Chrome:
//
//   Click the three-dot menu icon on the top-right of the browser.
//   Click "More tools", then "Developer tools".
//   Click the "Application" tab.
//   In the left sidebar, click "Cookies" under "Storage".
//   Click on your website.
//   Find the 'cookie_consent' cookie in the table and click the "x" next to it to delete it.
//
//       Mozilla Firefox:
//
//   Click the menu icon on the top-right of the browser.
//   Click "Web Developer", then "Storage Inspector".
//   In the left sidebar, click "Cookies" under "Storage".
//   Click on your website.
//   Find the 'cookie_consent' cookie in the table and right-click it.
//   Click "Delete".
//
//       Safari:
//
// Click "Safari" in the top menu, then "Preferences".
//   Click the "Privacy" tab.
//   Click "Manage Website Data...".
//   Find your website and click "Remove".

//      Microsoft Edge:
//
// Click the three-dot menu icon on the top-right of the browser.
// Click "Settings".
// Click "Cookies and site permissions" on the left sidebar.
// Click "Cookies and site data".
// Click "See all cookies and site data".
// Search for your website in the search bar at the top.
// Click on the arrow icon next to your website.
// Find the 'cookie_consent' cookie in the list and click the trash bin icon next to it to delete it.
