import { Component, Input, ElementRef } from '@angular/core';
import { User } from "../../models/user";
import { CartService } from "../../_services/cart.service";

@Component({
  selector: 'app-people-page-card',
  templateUrl: './people-page-card.component.html',
  styleUrls: ['./people-page-card.component.css']
})

export class PeoplePageCardComponent {

  // @ts-ignore
  @Input() user: User;
  isOpen: boolean = false;

  constructor(
    private cartService: CartService,
    private elementRef: ElementRef
  ) { }

  addToCart(user: User) {
    this.cartService.addToCart(user);
  }

  toggleLeftContainer() {
    const arr = this.elementRef.nativeElement.querySelector(".arr-container");
    const leftContainer = this.elementRef.nativeElement.querySelector(".left-container");

    if (!this.isOpen) {
      arr.classList.add("active-arr");
      leftContainer.classList.remove("off");
      leftContainer.classList.add("active");
    } else {
      arr.classList.remove("active-arr");
      leftContainer.classList.remove("active");
      leftContainer.classList.add("off");
    }

    this.isOpen = !this.isOpen;
  }

  ngAfterViewInit() {
    const arr = this.elementRef.nativeElement.querySelector(".arr-container");
    const clc = this.elementRef.nativeElement.querySelector(".cancel");

    arr.addEventListener("click", () => {
      this.toggleLeftContainer();
    });

    clc.addEventListener("click", () => {
      this.toggleLeftContainer();
    });
  }
}
