import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from "./components/login/login.component";
import { MainPageComponent } from "./components/main-page/main-page.component";
import { PeoplePageComponent } from "./components/people-page/people-page.component";
import { ProfilePageComponent } from "./components/profile-page/profile-page.component";
import { RegisterComponent } from "./components/register/register.component";
import { IntroComponent } from "./components/intro/intro.component";
import { PageNotFoundComponent } from "./components/page-not-found/page-not-found.component";
import { UserSettingsPageComponent } from './components/user-settings-page/user-settings-page.component';
import { TermsOfUseComponent } from './components/terms-of-use/terms-of-use.component';
import { ResetPasswordComponent } from './components/reset-password/reset-password.component';
import { ChangePasswordComponent } from "./components/change-password/change-password.component";
import { CartComponent } from "./components/cart/cart.component";
import { CartCheckoutComponent } from "./components/cart-checkout/cart-checkout.component";
import { CreatorsComponent } from "./components/creators/creators.component";
import { PersonalityInfoPageComponent } from "./components/personality-info-page/personality-info-page.component";

const routes: Routes = [
  { path: '', component: IntroComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'main', component: MainPageComponent },
  { path: 'people', component: PeoplePageComponent },
  { path: 'profile/:username', component: ProfilePageComponent },
  { path: 'update-profile', component: UserSettingsPageComponent },
  { path: 'reset-password', component: ResetPasswordComponent },
  { path: 'change-password', component: ChangePasswordComponent },
  { path: 'termsOfUse', component: TermsOfUseComponent },
  { path: 'cart', component: CartComponent },
  { path: 'checkout', component: CartCheckoutComponent },
  { path: 'creators', component: CreatorsComponent },
  { path: 'personality-test', component: PersonalityInfoPageComponent },
  { path: '**', pathMatch: 'full', component: PageNotFoundComponent }
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
