import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
const API_URL = 'http://localhost:8080/api/v1/'

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) {}

  getPublicContent():Observable<any> {
    return this.http.get(API_URL + '....', {responseType: "text"})
    // needs to be edited
  }
  getUserContent():Observable<any> {
    return this.http.get(API_URL + '....', {responseType: "json"})
  }
}
