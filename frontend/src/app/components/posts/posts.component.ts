import {Component, Input, OnInit} from '@angular/core';
import {PostsService} from "../../_services/posts.service";
import {Post} from "../../models/post";

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit {
  @Input() userRole!: string;
  @Input() userFullName!: string;
  @Input() currentUserId!: number | undefined;


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
    if (this.currentUserId === undefined) {
      // Handle the error: maybe show a message to the user, or just return
      return;
    }
    this.postsService
      .createPost(this.currentUserId, this.userFullName, text, parentPostId)
      .subscribe((createdPost) => {
        this.loadPosts();
      });
  }

  loadPosts() {
    this.postsService.getPosts().subscribe((posts: Post[]) => {
      this.posts = posts;
    })
  }

  updatePost({text, id}: {text: string, id: number}) {
    this.postsService
      .updatePost(id, text)
      .subscribe((updatedPost) => {
        this.posts = this.posts.map((post) => {
          if (post.id === id) {
            this.loadPosts();
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

  likePost(postId: number) {
    this.postsService.likePost(postId).subscribe(
      response => {
        console.log('Post liked successfully', response);
        // TODO: Update the UI to reflect the new like
      },
      error => {
        console.log('Error liking post', error);
      }
    );
  }
}
