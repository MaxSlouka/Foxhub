import {Component, Input, OnInit, ChangeDetectorRef} from '@angular/core';
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
  @Input() userRole!: string;
  @Input() userFullName!: string;


  posts: Post[] = [];
  activePost: Post | null = null;

  constructor(private postsService: PostsService, private cdr: ChangeDetectorRef) {
  }


  ngOnInit() {
    this.loadPosts();
  }

  loadPosts() {
    this.postsService.getPosts().subscribe((posts: Post[]) => {
      this.posts = posts;
    })
  }


  addPost({text, parentPostId}: {text: string, parentPostId: null | number }): void {

    this.postsService
      .createPost(this.userID, this.userFullName, text, parentPostId)
      .subscribe((createdPost) => {
        this.loadPosts(); // reload posts after adding a new one
      });
  }


  updatePost({text, id}: {text: string, id: number}) {
    this.postsService.updatePost(id, text).subscribe(
      (updatedPost) => {
        this.loadPosts(); // reload posts after updating a post

        this.activePost = null;
      },
      (error) => {
        console.error('error updating post', error);
      }
    );
  }

  deletePost(postId: number): void {
    this.postsService.deletePost(postId).subscribe(() => {
      this.posts = this.posts.filter(
        (post) => post.id !== postId
      );
      this.cdr.detectChanges(); // Trigger change detection
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
