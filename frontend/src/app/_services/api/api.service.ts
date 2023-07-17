import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {User} from "../../models/user";

const httpOptions = {
  headers: new HttpHeaders({'Content-type': 'application/json'})
}

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private apiUrlGetAll: string = "http://localhost:8080/api/v1/public/people";


  constructor(private http: HttpClient) {
  }

  getAll(): Observable<User[]> {
    return this.http.get<User[]>(this.apiUrlGetAll)
  }

  getUserBasicInfo(): Observable<User> {
    return this.http.get<User>('http://localhost:8080/api/v1/user/person');
  }

  deleteUser(): Observable<any> {
    return this.http.delete('http://localhost:8080/api/v1/user/people');
  }

  updateUser(firstName: string, lastName: string, email: string, github: string, linkedin: string, facebook: string, twitter: string, instagram: string): Observable<any> {
    return this.http.patch("http://localhost:8080/api/v1/user/people", {
        firstName,
        lastName,
        email,
        github,
        linkedin,
        facebook,
        twitter,
        instagram
      },
      httpOptions
    );
  }
}
