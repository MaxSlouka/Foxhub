import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import {User} from "../user";
@Injectable({
  providedIn: 'root'
})
export class PeopleService {

  constructor(private http: HttpClient) {

  }

  getAll(): Observable<any> {
    // @ts-ignore
    return this.http.get("http://localhost:8080/api/v1/people",{responseType: JSON})
  }
}
