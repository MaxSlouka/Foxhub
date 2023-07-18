import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {Post} from "../models/post";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class PostsService {
  constructor(private httpClient: HttpClient) {
  }

  getPosts(): Observable<Post[]> {
    return this.httpClient.get<Post[]>('http://localhost:8080/api/v1/public/posts');
  }

  createPost(text: string, parentPostId: null | number): Observable<Post> {
    return this.httpClient.post<Post>('http://localhost:8080/api/v1/admin/posts', {
        content: text,
        parentPostId
      }
    )
  }
}
