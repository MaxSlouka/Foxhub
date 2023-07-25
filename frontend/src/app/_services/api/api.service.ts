import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {User} from "../../models/user";
import {Language} from "../../models/language";
import {Technology} from "../../models/technology";

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


  search(user: any, key: string, results: any[]) {
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
             completeProjects: string | undefined,
             yearsOfExperience: string | undefined,
             phone: string | undefined,
             location: string | undefined,
             about: string | undefined,
             gitHub: string | undefined,
             linkedin: string | undefined,
             facebook: string | undefined,
             instagram: string | undefined,
             optionalPage: string | undefined,
             languages: Language[] | undefined,
             technologies: Technology[] | undefined,
             workStatus:boolean | undefined
  ): Observable<any> {

    return this.http.patch("http://localhost:8080/api/v1/user/people", {
        firstName,
        lastName,
        completeProjects,
        yearsOfExperience,
        phone,
        location,
        about,
        gitHub,
        linkedin,
        facebook,
        instagram,
        languages,
      technologies,
      workStatus
      },
      httpOptions
    );
  }
}
