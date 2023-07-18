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

  search(user: User, key:string, results: User[]) {
    let hasTechnologyMatch = false;

    // @ts-ignore
    for (const technology of user.technologies) {
      if (technology.name.toLowerCase().includes(key.toLowerCase())) {
        results.push(user);
        hasTechnologyMatch = true;
        break;
      }
    }

    if (!hasTechnologyMatch) {
      if (
        user.firstName.toLowerCase().includes(key.toLowerCase()) ||
        user.lastName.toLowerCase().includes(key.toLowerCase())
      ) {
        results.push(user);
      }
    }
  }

  updateUser(firstName: string,
             lastName: string,
             email: string,
             github: string | undefined,
             linkedin: string | undefined,
             facebook: string | undefined,
             instagram: string | undefined
  ): Observable<any> {
    return this.http.patch("http://localhost:8080/api/v1/user/people", {
        firstName,
        lastName,
        email,
        github,
        linkedin,
        facebook,
        instagram
      },
      httpOptions
    );
  }
}
