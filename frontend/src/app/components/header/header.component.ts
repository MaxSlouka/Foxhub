import {Component, OnInit, Input} from '@angular/core';
import {AuthService} from "../../_services/auth.service";
import {StorageService} from "../../_services/storage.service";
import {User} from "../../models/user";
import {DataService} from "../../_services/api/data.service";
import {ApiService} from "../../_services/api/api.service";
import {CartService} from "../../_services/cart.service";

// @ts-ignore
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})

export class HeaderComponent implements OnInit {

  // @ts-ignore
  username: string | null = "";

  // @ts-ignore
  users: User[];
  user: User = {email: "", firstName: "", lastName: "", password: ""};

  isLoggedIn = false;

  userEmail: string = '';


  // @ts-ignore
  countItems: number = this.cartService.getCartItems().length;



  constructor(private authService: AuthService,
              private storageService: StorageService,
              private apiService: ApiService,
              public dataService: DataService,
              public cartService: CartService) {
  }

  ngOnInit(): void {
    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
      this.userEmail = this.storageService.getUserFromSession();
      this.apiService.getUserBasicInfo().subscribe((user: User) => {
        this.user = user;
      });
    }
  }

  handleDataFromChild(data: any) {
    this.users = data;
  }


  logout(): void {
    this.storageService.logout();
    this.authService.logout();
    this.isLoggedIn = false;
  }

  protected readonly caches = caches;
}
