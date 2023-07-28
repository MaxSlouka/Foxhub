import { Component, Input, ElementRef} from '@angular/core';
import { User } from "../../models/user";
import { CartService } from "../../_services/cart.service";
import { StorageService } from "../../_services/storage.service";
import { ApiService } from "../../_services/api/api.service";
import { AuthService } from "../../_services/auth.service";

@Component({
  selector: 'app-people-page-card',
  templateUrl: './people-page-card.component.html',
  styleUrls: ['./people-page-card.component.css']
})

export class PeoplePageCardComponent {

  // @ts-ignore
  @Input() user: User;
  // @ts-ignore
  users: User[];
  userEmail: string = '';
  isOpen: boolean = false;
  isLoggedIn = false;
  // @ts-ignore
  @Input addedUsers: User[] = [];
  // @ts-ignore
  isAdded: boolean = false;

  constructor(
    private authService: AuthService,
    private cartService: CartService,
    private elementRef: ElementRef,
    private storageService: StorageService,
    private apiService: ApiService,
  ) { }

  isUserAdded(user: User) {
    this.isAdded = this.addedUsers.some(item => item.nickname === user.nickname);
  }

  addToCart(user: User) {
    this.cartService.addToCart(user);
    this.isUserAdded(user);
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

  ngOnInit(): void {
    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
      this.userEmail = this.storageService.getUserFromSession();
    }
  }

  logout(): void {
    this.storageService.logout();
    this.authService.logout();
    this.isLoggedIn = false;
  }
}
