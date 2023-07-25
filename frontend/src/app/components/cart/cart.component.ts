import {Component, OnInit} from '@angular/core';
import {CartService} from "../../_services/cart.service";
import {User} from "../../models/user";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit{
  private cartItems:User[] = [];


  constructor(private cartService:CartService) {

  }
  ngOnInit(): void {
    this.cartItems = this.cartService.getCartItems();

  }

  show() {
    this.cartItems = this.cartService.getCartItems();

    console.log(this.cartItems);
  }
}
