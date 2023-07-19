import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
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
import { NgOptimizedImage } from "@angular/common";
import { IntroComponent } from './components/intro/intro.component';
import { httpInterceptorProviders } from "./_helpers/interceptor";
import { PeoplePageCardComponent } from "./components/people-page-card/people-page-card.component";
import { PeoplePageSearchbarComponent } from "./components/people-page-searchbar/people-page-searchbar.component";
import { TechnologyComponent } from './components/technology/technology.component';
import { LanguageComponent } from './components/language/language.component';
import { BackToTopBtnComponent } from './components/back-to-top-btn/back-to-top-btn.component';
import { AsideComponent } from './components/aside/aside.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { UserSettingsPageComponent } from './components/user-settings-page/user-settings-page.component';
import { TermsOfUseComponent } from './components/terms-of-use/terms-of-use.component';
import { HeaderSearchbarComponent } from './components/header-searchbar/header-searchbar.component';
import { UpdatePasswordComponent } from './components/update-password/update-password.component';
import { HeaderSearchbarCardComponent } from './components/header-searchbar-card/header-searchbar-card.component';
import { QRCodeModule } from 'angularx-qrcode';

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
    LanguageComponent,
    BackToTopBtnComponent,
    AsideComponent,
    PeoplePageSearchbarComponent,
    PageNotFoundComponent,
    UserSettingsPageComponent,
    TermsOfUseComponent,
    HeaderSearchbarComponent,
    UpdatePasswordComponent,
    HeaderSearchbarCardComponent
  ],
  imports: [
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    NgOptimizedImage,
    HttpClientModule,
    ReactiveFormsModule,
    QRCodeModule
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})

export class AppModule {
}
