import { Injectable } from '@angular/core';
import { User } from "../models/user";
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class CartService {
  cartItems: User[] = [];
  private cartItemsSubject = new Subject<number>();

  constructor() {
    let storedCart = sessionStorage.getItem('cart');
    if (storedCart) {
      this.cartItems = JSON.parse(storedCart);
    }
  }

  addToCart(user: User) {
    if (!this.cartItems.some(item => item.nickname === user.nickname)) {
      this.cartItems.push(user);
      this.updateLocalStorage();
      this.cartItemsSubject.next(this.cartItems.length); // Notify subscribers about the change
    }
  }

  removeFromCart(user: User) {
    const index = this.cartItems.indexOf(user);
    if (index > -1) {
      this.cartItems.splice(index, 1);
      this.updateLocalStorage();
      this.cartItemsSubject.next(this.cartItems.length); // Notify subscribers about the change
    }
  }

  getCartItems() {
    return this.cartItems;
  }

  getCartItemsObservable() {
    return this.cartItemsSubject.asObservable();
  }

  private updateLocalStorage() {
    sessionStorage.setItem('cart', JSON.stringify(this.cartItems));
  }
}
