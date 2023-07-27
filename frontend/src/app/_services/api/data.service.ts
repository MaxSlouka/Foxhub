import { EventEmitter, Injectable } from '@angular/core';
import { User } from "../../models/user";

@Injectable({
  providedIn: 'root'
})
export class DataService {

  // @ts-ignore
  users: User[];
  prefix: string = "";
  // prefix: string = "http://localhost:8080/";

  constructor() {
  }

}
