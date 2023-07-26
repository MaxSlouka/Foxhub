import { Component, Input, Renderer2, ElementRef } from '@angular/core';
import { User } from "../../models/user";
import {CartService} from "../../_services/cart.service";

@Component({
  selector: 'app-people-page-card',
  templateUrl: './people-page-card.component.html',
  styleUrls: ['./people-page-card.component.css']
})

export class PeoplePageCardComponent {
  // @ts-ignore
  @Input() user: User;
  isOpen: boolean = false;

  constructor(private renderer: Renderer2, private el: ElementRef, private cartService:CartService) { }

  ngOnInit() {
    const btnElement = this.el.nativeElement.querySelector('.btn-opener');
    const boxElement = this.el.nativeElement.querySelector('.box');

    this.renderer.listen(btnElement, 'click', () => {
      this.isOpen = !this.isOpen;
      if (this.isOpen) {
        this.renderer.addClass(btnElement, 'active');
        this.renderer.addClass(boxElement, 'open');
      } else {
        this.renderer.removeClass(btnElement, 'active');
        this.renderer.removeClass(boxElement, 'open');
      }
    });
  }

  addToCart(user: User) {
    this.cartService.addToCart(user);

  }
}
