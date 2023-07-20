import {Component} from '@angular/core';
import {ApiService} from "./_services/api/api.service";
import {DataService} from "./_services/api/data.service";
import {CookieService} from 'ngx-cookie-service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'frontend';
  public cookie_name = '';
  public all_cookies: any = '';
  public showCookiePopup = false;

  // @ts-ignore
  constructor(private apiService: ApiService,
              private dataService: DataService,
              private cookieService: CookieService) {
  }

  setCookie() {
    this.cookieService.set('Foxbook', 'Welcome to Foxbook!');
  }

  deleteCookie() {
    this.cookieService.delete('Foxbook');
  }

  deleteAll() {
    this.cookieService.deleteAll();
  }

  acceptCookies() {
    this.cookieService.set('cookie_consent', 'true');
    this.showCookiePopup = false;
  }

  declineCookies() {
    this.cookieService.set('cookie_consent', 'false');
    this.showCookiePopup = false;
  }

  ngOnInit(): void {
    this.getUsers();
    this.cookie_name = this.cookieService.get('Foxbook');
    this.all_cookies = this.cookieService.getAll();
    this.showCookiePopup = !this.cookieService.check('cookie_consent');
  }

  private getUsers(): void {
    this.apiService
      .getAll().subscribe((users) => {
        this.dataService.users = users;
      }
    );
  }
}
