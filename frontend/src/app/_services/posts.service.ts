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

  createPost(userID: number | undefined, username: string, text: string, parentPostId: number | null): Observable<Post> {
    return this.httpClient.post<Post>(
      'http://localhost:8080/api/v1/admin/posts', {
        userID: userID,
        username: username,
        content: text,
        createdAt: new Date().toISOString(),
        parentPostId,
      }
    );
  }

  updatePost(id: number, text: string): Observable<Post> {
    return this.httpClient.put<Post>(
      `http://localhost:8080/api/v1/admin/posts/${id}`,
      {
        content: text,
      }
    );
  }

  deletePost(id: number): Observable<{}> {
    return this.httpClient.delete(
      `http://localhost:8080/api/v1/admin/posts/${id}`)
  }
}
