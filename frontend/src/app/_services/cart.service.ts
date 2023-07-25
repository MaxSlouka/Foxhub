import { Injectable } from '@angular/core';
import {User} from "../models/user";

@Injectable({
  providedIn: 'root'
})
export class CartService {
  cartItems:User[] = [];


  constructor() {
    let storedCart = localStorage.getItem('cart');
    if (storedCart) {
      this.cartItems = JSON.parse(storedCart);
    }
  }

  addToCart(user:User){
    this.cartItems.push(user);
    this.updateLocalStorage();
  }
  getCartItems() {
    return this.cartItems;
  }
  private updateLocalStorage() {
    localStorage.setItem('cart', JSON.stringify(this.cartItems));
  }


}
