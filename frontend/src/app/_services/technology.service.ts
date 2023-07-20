import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Language} from "../models/language";
import {HttpClient} from "@angular/common/http";
import {Technology} from "../models/technology";


@Injectable({
  providedIn: 'root'
})
export class TechnologyService {

  readonly urlTechnology: string = "http://localhost:8080/api/v1/public/technologies";

  constructor(private http: HttpClient) { }

  getAll(): Observable<Technology[]> {
    return this.http.get<Technology[]>(this.urlTechnology);
  }
}
