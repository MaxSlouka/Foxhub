import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import {User} from "../models/user";



@Injectable({
  providedIn: 'root'
})
export class PeopleService {

  private apiUrl: string = "http://localhost:8080/api/v1/public/people";

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<User[]> {
    return this.http.get<User[]>(this.apiUrl)
  }
}