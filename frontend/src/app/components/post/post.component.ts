import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Post} from "../../models/post";
import {PostsService} from "../../_services/posts.service";
import { AuthService } from "../../_services/auth.service";
import { StorageService } from "../../_services/storage.service";
import { ApiService } from "../../_services/api/api.service";
import { User } from "../../models/user";

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {
  @Input() post!: Post;
  @Input() replies!: Post[];
  @Input() activePost!: Post | null;
  @Input() parentPostId!: number | null;
  @Input() userRole!: string;
  @Input() currentUserId!: number | undefined;
  @Output() setActivePost = new EventEmitter<Post | null>();
  @Output() addPost = new EventEmitter<{ text: string, parentPostId: number | null }>();
  @Output() updatePost = new EventEmitter<{ text: string, id: number }>();
  @Output() deletePost = new EventEmitter<number>();

  canReply: boolean = false;
  canEdit: boolean = false;
  canDelete: boolean = false;
  replyId: number | null = null;

  // @ts-ignore
  users: User[];
  user: User = { email: "", firstName: "", lastName: "", password: "" };
  userEmail: string = '';
  isLoggedIn = false;

  constructor(
    private postService: PostsService,
    private storageService: StorageService,
    private apiService: ApiService,
    private authService: AuthService
    ) {
  }

  ngOnInit(): void {
    const timeToEdit = 300000;
    const timePassed =
      new Date().getMilliseconds() -
      new Date(this.post.createdAt).getMilliseconds() > timeToEdit;

    this.canReply = Boolean(this.currentUserId);
    this.canEdit = this.currentUserId === this.post.userId && !timePassed;
    this.canDelete = this.currentUserId === this.post.userId && this.replies.length === 0;
    this.loadPost();

    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
      this.userEmail = this.storageService.getUserFromSession();
      this.apiService.getUserBasicInfo().subscribe((user: User) => {
        this.user = user;
      });
    }
  }

  loadPost(): void {
    this.postService.getPosts().subscribe((posts: Post[]) => {
      const updatedPost = posts.find(p => p.id === this.post.id);
      if (updatedPost) {
        this.post = updatedPost;
      }
    });
  }


  isReplying(): Boolean {
    if (!this.activePost) {
      return false;
    }
    return this.activePost.id === this.post.id;
  }

  isEditing = false;

  startEditing() {
    this.isEditing = true;
  }

  stopEditing() {
    this.isEditing = false;
  }

  commentText = '';

  commentPost() {
    this.postService.commentPost(this.post.id, this.commentText).subscribe(
      response => {
        console.log('Comment added successfully', response);
        this.commentText = '';
        this.loadPost();
      },
      error => {
        console.log('Error adding comment', error);
      }
    );
  }

  likePost(postId: number) {
    this.postService.likePost(postId).subscribe(
      response => {
        console.log('Post liked successfully', response);
        this.loadPost();
        this.post.isLikedByCurrentUser = true;
      },
      error => {
        console.log('Error liking post', error);
      }
    );
  }
}
