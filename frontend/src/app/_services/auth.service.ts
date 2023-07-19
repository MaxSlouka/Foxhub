import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";

const AUTH_API = 'http://localhost:8080/api/v1/auth/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-type': 'application/json' })
}

@Injectable({
  providedIn: 'root'
})

export class AuthService {

  constructor(private http: HttpClient) {
  }

  login(email: string, password: string): Observable<any> {
    return this.http.post(
      AUTH_API + 'login',
      {
        email,
        password
      },
      httpOptions
    );
  }

  register(firstName: string, lastName: string, email: string, password: string): Observable<any> {
    return this.http.post(
      AUTH_API + 'register',
      {
        firstName,
        lastName,
        email,
        password
      },
      httpOptions
    );
  }

  logout(): void {
    this.http.post(
      'http://localhost:8080/api/v1/auth/signout',
      {},
      httpOptions
    ).subscribe(ok => {
    });
  }
}
