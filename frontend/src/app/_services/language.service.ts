import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Language} from "../models/language";
import {HttpClient} from "@angular/common/http";


@Injectable({
  providedIn: 'root'
})
export class LanguageService {

  readonly urlLanguage: string = "http://localhost:8080/api/v1/public/languages";

  constructor(private http: HttpClient) { }

  getAll(): Observable<Language[]> {
    return this.http.get<Language[]>(this.urlLanguage);
  }
}
