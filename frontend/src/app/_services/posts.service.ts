import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {Post} from "../models/post";
import {HttpClient} from "@angular/common/http";
import { GlobalConstants } from "../common/global-constants";

const prefix = GlobalConstants.prefix;

@Injectable({
  providedIn: 'root'
})
export class PostsService {
  constructor(private httpClient: HttpClient) {
  }

  getPosts(): Observable<Post[]> {
    return this.httpClient.get<Post[]>(prefix + '/api/v1/public/posts');
  }

  createPost(userID: number, username: string, text: string, parentPostId: number | null): Observable<Post> {
    return this.httpClient.post<Post>(
      prefix + '/api/v1/admin/posts', {
        userID: userID,
        username: username,
        content: text,
        createdAt: new Date().toISOString(),
        parentPostId,
      }
    );
  }

  updatePost(id: number, text: string): Observable<Post> {
    return this.httpClient.patch<Post>(
       prefix + `/api/v1/admin/posts/${id}`,
      {
        content: text,
      }
    );
  }

  deletePost(id: number): Observable<{}> {
    return this.httpClient.delete(
      prefix + `/api/v1/admin/posts/${id}`)
  }
  likePost(postID: number): Observable<any> {
    return this.httpClient.post<any>(prefix + `/api/v1/user/posts/${postID}/like`, {});
  }

  commentPost(postId: number, commentText: string): Observable<any> {
    return this.httpClient.post<any>(
      prefix + `/api/v1/user/post/${postId}/comment`,
      { content: commentText }
    );
  }
}
