import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {MainPageComponent} from "./components/main-page/main-page.component";
import {PeoplePageComponent} from "./components/people-page/people-page.component";
import {ProfilePageComponent} from "./components/profile-page/profile-page.component";
import {RegisterComponent} from "./components/register/register.component";
import {IntroComponent} from "./components/intro/intro.component";
import {PageNotFoundComponent} from "./components/page-not-found/page-not-found.component";
import { UserSettingsPageComponent } from './components/user-settings-page/user-settings-page.component';

const routes: Routes = [
  {path: '', component: IntroComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'main', component: MainPageComponent},
  {path: 'people', component: PeoplePageComponent},
  {path: 'profile', component: ProfilePageComponent},

  {path: 'profile/:username', component: ProfilePageComponent},
  {path: '**', pathMatch: 'full', component: PageNotFoundComponent}
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
