import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {User} from "../../models/user";

@Injectable({
  providedIn: 'root'
})
export class RequestsService {


  constructor(private http: HttpClient) { }

  getUserBasicInfo():Observable<User>{

    return this.http.get<User>('http://localhost:8080/api/v1/user/person');

  }
}
