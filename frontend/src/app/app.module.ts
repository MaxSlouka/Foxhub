import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from "@angular/common/http";
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { MainPageComponent } from './components/main-page/main-page.component';
import { ProfilePageComponent } from './components/profile-page/profile-page.component';
import { PeoplePageComponent } from './components/people-page/people-page.component';
import {NgOptimizedImage} from "@angular/common";
import { IntroComponent } from './components/intro/intro.component';
import {httpInterceptorProviders} from "./_helpers/interceptor";
import {PeoplePageCardComponent} from "./components/people-page-card/people-page-card.component";
import { TechnologyComponent } from './components/technology/technology.component';
import { LaguageComponent } from './components/laguage/laguage.component';
import { BackToTopBtnComponent } from './components/back-to-top-btn/back-to-top-btn.component';
import { AsideComponent } from './components/aside/aside.component';
import { PeoplePageSearchbarComponent } from './components/people-page-searchbar/people-page-searchbar.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    RegisterComponent,
    MainPageComponent,
    ProfilePageComponent,
    PeoplePageComponent,
    IntroComponent,
    PeoplePageCardComponent,
    TechnologyComponent,
    LaguageComponent,
    BackToTopBtnComponent,
    AsideComponent,
    PeoplePageSearchbarComponent,

  ],
  imports: [
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    NgOptimizedImage,
    HttpClientModule
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
