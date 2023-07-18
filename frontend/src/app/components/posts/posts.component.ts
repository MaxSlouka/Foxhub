import {Component, Input, OnInit} from '@angular/core';
import {PostsService} from "../../_services/posts.service";
import {Post} from "../../models/post";

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit {
  @Input() currentUserId!: number;

  posts: Post[] = [];
  activePost: Post | null = null;

  constructor(private postsService: PostsService) {
  }

  ngOnInit() {
    this.postsService.getPosts().subscribe((posts: Post[]) => {
      this.posts = posts;
      console.log(posts);
    })
  }

  addPost({text, parentPostId}: { text: string, parentPostId: null | number }): void {
    console.log('addPost', text, parentPostId);
    this.postsService
      .createPost(text, parentPostId)
      .subscribe((createdPost) => {
        this.posts = [...this.posts, createdPost];
        this.activePost = null;
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
