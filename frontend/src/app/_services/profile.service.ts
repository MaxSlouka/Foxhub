import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../user";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  private apiUrl: string = "http://localhost:8080/api/v1/public/person/";

  constructor(private http: HttpClient) {
  }

  // @ts-ignore
  getUser(username: string | null): Observable<User> {
    return this.http.get<User>(this.apiUrl + username);
  }
}
