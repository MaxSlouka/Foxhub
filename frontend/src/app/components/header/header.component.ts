import {Component, OnInit, OnDestroy} from '@angular/core';
import {AuthService} from "../../_services/auth.service";
import {StorageService} from "../../_services/storage.service";
import {User} from "../../models/user";
import {ApiService} from "../../_services/api/api.service";
import {CartService} from "../../_services/cart.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})

export class HeaderComponent implements OnInit, OnDestroy {

  // @ts-ignore
  username: string | null = "";

  // @ts-ignore
  users: User[];
  user: User = {email: "", firstName: "", lastName: "", password: ""};

  isLoggedIn = false;

  userEmail: string = '';


  // @ts-ignore
  countItems: number = this.cartService.getCartItems().length;
  // @ts-ignore
  private cartItemsSubscription: Subscription;

  constructor(
    private authService: AuthService,
    private storageService: StorageService,
    private apiService: ApiService,
    private cartService: CartService
  ) {
  }

  ngOnInit(): void {
    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
      this.userEmail = this.storageService.getUserFromSession();
      this.apiService.getUserBasicInfo().subscribe((user: User) => {
        this.user = user;
      });
    }
    this.cartItemsSubscription = this.cartService.getCartItemsObservable()
      .subscribe((cartItems: number) => {
        this.countItems = cartItems;
      });
  }

  handleDataFromChild(data: any) {
    this.users = data;
  }

  logout(): void {
    this.storageService.logout();
    this.authService.logout();
    this.isLoggedIn = false;
  }

  ngOnDestroy() {
    // Unsubscribe from the observable to avoid memory leaks
    this.cartItemsSubscription.unsubscribe();
  }
}
