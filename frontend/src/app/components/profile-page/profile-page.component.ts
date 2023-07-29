import { Component } from '@angular/core';
import { User } from "../../models/user";
import { ProfileService } from "../../_services/profile.service";
import { ActivatedRoute, Router, NavigationEnd } from '@angular/router';
import { SafeValue } from "@angular/platform-browser";
import { filter } from 'rxjs/operators';
import { StorageService } from "../../_services/storage.service";
import { GlobalConstants } from "../../common/global-constants";
import { CartService } from "../../_services/cart.service";

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css']
})

export class ProfilePageComponent {
  // @ts-ignore
  username: string | null = "";
  userEmail: string = '';
  user: User = { email: "", firstName: "", lastName: "", password: "" };
  qrCodeDownloadLink: SafeValue = "";
  isOpen: boolean = false;
  isLoggedIn: boolean = false;
  prefix: string = GlobalConstants.prefix;
  addedUsers: User[] = this.cartService.getCartItems();
  // @ts-ignore
  private cartItemsSubscription: Subscription;

  constructor(
    private profileService: ProfileService,
    private activatedroute: ActivatedRoute,
    private storageService: StorageService,
    private cartService: CartService,
    private router: Router
  ) {
    router.events.pipe(
      filter(event => event instanceof NavigationEnd)
    ).subscribe(event => {
      this.ngOnInit();
    });
  }

  ngOnInit() {
    this.username = this.activatedroute.snapshot.paramMap.get("username");
    this.profileService.getUser(this.username).subscribe(user => {
      this.user = user;
      const cartItems = this.cartService.getCartItems();
      this.user.inCart = cartItems.some((cartUser: User) => cartUser.nickname === this.user.nickname);
    });

    // @ts-ignore
    this.onChangeURL();

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

  toggleSocialLinks() {
    this.isOpen = !this.isOpen;
  }

  onChangeURL(url: SafeValue) {
    let qrCodeURL = '/profile/' + this.username;
    this.qrCodeDownloadLink = url;
  }

  isUserAdded(user: User) {
    this.user.inCart = true;
  }

  addToCart(user: User) {
    this.cartService.addToCart(user);
    this.isUserAdded(user);
  }
}
