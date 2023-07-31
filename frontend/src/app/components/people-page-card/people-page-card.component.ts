import { Component, Input, ElementRef, OnDestroy } from '@angular/core';
import { User } from "../../models/user";
import { CartService } from "../../_services/cart.service";
import { StorageService } from "../../_services/storage.service";
import { AuthService } from "../../_services/auth.service";
import { Subscription } from "rxjs";

@Component({
  selector: 'app-people-page-card',
  templateUrl: './people-page-card.component.html',
  styleUrls: ['./people-page-card.component.css']
})

export class PeoplePageCardComponent implements OnDestroy {

  // @ts-ignore
  @Input() user: User;
  // @ts-ignore
  users: User[];
  userEmail: string = '';
  isLoggedIn = false;
  addedUsers: User[] = this.cartService.getCartItems();

  // @ts-ignore
  private cartItemsSubscription: Subscription;

  constructor(
    private authService: AuthService,
    private cartService: CartService,
    private elementRef: ElementRef,
    private storageService: StorageService,
  ) { }

  isUserAdded(user: User) {
    this.user.inCart = true;
  }

  addToCart(user: User) {
    this.cartService.addToCart(user);
    this.isUserAdded(user);
  }

  ngOnInit(): void {
    if (this.addedUsers.find((user: User) => user.nickname === this.user.nickname)) {
      this.user.inCart = true;
    }
    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
      this.userEmail = this.storageService.getUserFromSession();
    }
    this.cartItemsSubscription = this.cartService.getCartItemsObservable()
      .subscribe((cartItems: User[]) => {
        this.addedUsers = cartItems;
      });
  }

  ngOnDestroy() {
    this.cartItemsSubscription.unsubscribe();
  }

  logout(): void {
    this.storageService.logout();
    this.authService.logout();
    this.isLoggedIn = false;
  }
}
