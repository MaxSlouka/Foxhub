import {Component, Input, OnDestroy} from '@angular/core';
import {User} from "../../models/user";
import {CartService} from "../../_services/cart.service";
import {StorageService} from "../../_services/storage.service";
import {AuthService} from "../../_services/auth.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-people-page-card',
  templateUrl: './people-page-card.component.html',
  styleUrls: ['./people-page-card.component.css']
})

export class PeoplePageCardComponent implements OnDestroy {

  // @ts-ignore
  @Input() user: User;
  isLoggedIn = false;

  constructor(
    private authService: AuthService,
    private cartService: CartService,
    private storageService: StorageService,
  ) {
  }

  ngOnInit(): void {
    if (this.cartService.getCartItems().find((user: User) => user.nickname === this.user.nickname)) {
      this.user.inCart = true;
    }
    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
    }
  }

  ngOnDestroy() {
  }


  addToCart(user: User) {
    this.cartService.addToCart(user);
    this.user.inCart = true;
  }

  removeItem(user: User) {
    this.cartService.removeFromCart(user)
    this.user.inCart = false;
  }

  toggleItem(event: Event, user: User) {
    event.stopPropagation();
    this.user.inCart ? this.removeItem(user) : this.addToCart(user);
  }
}
