import { HttpClient } from "@angular/common/http";
import { GlobalConstants } from "../common/global-constants";
import { Observable } from "rxjs";
import { DonutFilling } from "../models/donutFilling";
import { Injectable } from "@angular/core";

const prefix = GlobalConstants.prefix;

@Injectable({
  providedIn: 'root'
})

export class DonutFillingService {
  readonly urlDonutFilling: string = prefix + "/api/v1/public/donutFillings";
  constructor(private http: HttpClient) { }

  getAll(): Observable<DonutFilling[]> {
    return this.http.get<DonutFilling[]>(this.urlDonutFilling);
  }
}
