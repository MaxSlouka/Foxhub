import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {MainPageComponent} from "./main-page/main-page.component";
import {PeoplePageComponent} from "./people-page/people-page.component";
import {ProfilePageComponent} from "./profile-page/profile-page.component";
import {RegisterComponent} from "./register/register.component";
import {IntroComponent} from "./intro/intro.component";

const routes: Routes = [
  { path: '', component: IntroComponent},
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent},
  { path: 'main', component: MainPageComponent },
  { path: 'people', component: PeoplePageComponent},
  { path: 'profile', component: ProfilePageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
