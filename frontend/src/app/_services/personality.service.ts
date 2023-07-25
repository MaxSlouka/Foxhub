import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Personality} from "../models/personality";
@Injectable({
  providedIn: 'root'
})
export class PersonalityService {
  readonly urlPersonalities: string = "http://localhost:8080/api/v1/public/personalities";
  constructor(private http: HttpClient) { }

  getAll(): Observable<Personality[]> {
    return this.http.get<Personality[]>(this.urlPersonalities);
  }
}
