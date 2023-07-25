import { Injectable } from '@angular/core';
import {User} from "../models/user";

@Injectable({
  providedIn: 'root'
})
export class CartService {
  cartItems:User[] = [];

  constructor() {
    let storedCart = sessionStorage.getItem('cart');
    if (storedCart) {
      this.cartItems = JSON.parse(storedCart);
    }
  }

  addToCart(user:User){
    if (!this.cartItems.includes(user)){
      this.cartItems.push(user);
      this.updateLocalStorage();
    }
  }
  removeFromCart(user: User) {
    const index = this.cartItems.indexOf(user);
    if (index > -1) {
      this.cartItems.splice(index, 1);
      this.updateLocalStorage();
    }
  }

  getCartItems() {
    return this.cartItems;
  }
  private updateLocalStorage() {
    sessionStorage.setItem('cart', JSON.stringify(this.cartItems));
  }
}
