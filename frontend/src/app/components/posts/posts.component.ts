import {Component, Input, OnInit} from '@angular/core';
import {PostsService} from "../../_services/posts.service";
import {Post} from "../../models/post";
import {User} from "../../models/user";

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit {
  @Input() currentUserId!: number;
  // @ts-ignore
  @Input userRole: string;
  // @ts-ignore
  @Input() userFullName: string;

  posts: Post[] = [];
  activePost: Post | null = null;

  constructor(private postsService: PostsService) {
  }

  ngOnInit() {
    this.postsService.getPosts().subscribe((posts: Post[]) => {
      this.posts = posts;
    })
  }

  addPost({text, parentPostId}: {text: string, parentPostId: null | number }): void {
    console.log('addPost', text, parentPostId, this.userFullName);
    this.postsService
      .createPost(this.userFullName, text,parentPostId)
      .subscribe((createdPost) => {
      });
  }

  updatePost({text, id}: {text: string, id: number}) {
    this.postsService
      .updatePost(id, text)
      .subscribe((updatedPost) => {
        this.posts = this.posts.map((post) => {
          if (post.id === id) {
            return updatedPost;
          }
          return post;
        });
        this.activePost = null;
      });
  }

  deletePost(postId: number): void {
    this.postsService.deletePost(postId).subscribe(() => {
      this.posts = this.posts.filter(
        (post) => post.id !== postId
      );
    });
  }

  getReplies(postId: number): Post[] {
    return this.posts
      .filter((post) => post.parentPostId === postId)
      .sort(
        (a, b) =>
          new Date(a.createdAt!).getMilliseconds() -
          new Date(b.createdAt!).getMilliseconds()
      );
  }

  setActivePost(activePost: Post | null): void {
    this.activePost = activePost;
  }
}
