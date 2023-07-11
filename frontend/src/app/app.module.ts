import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import {HttpClientModule} from "@angular/common/http";
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { MainPageComponent } from './main-page/main-page.component';
import { ProfilePageComponent } from './profile-page/profile-page.component';
import { PeoplePageComponent } from './people-page/people-page.component';
import {NgOptimizedImage} from "@angular/common";
import { IntroComponent } from './intro/intro.component';
import {httpInterceptorProviders} from "./_helpers/interceptor";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    RegisterComponent,
    MainPageComponent,
    ProfilePageComponent,
    PeoplePageComponent
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
