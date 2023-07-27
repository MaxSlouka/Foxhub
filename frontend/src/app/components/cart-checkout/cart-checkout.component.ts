import { Component, OnInit } from '@angular/core';
import { User } from "../../models/user";
import { CartService } from "../../_services/cart.service";

@Component({
  selector: 'app-cart-checkout',
  templateUrl: './cart-checkout.component.html',
  styleUrls: ['./cart-checkout.component.css']
})

export class CartCheckoutComponent implements OnInit {
  cartItems: User[] = [];
  constructor(private cartService: CartService) { }

  ngOnInit(): void {
    this.cartItems = this.cartService.getCartItems();
  }

}
