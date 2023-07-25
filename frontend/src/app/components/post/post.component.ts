import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Post} from "../../models/post";
import {PostsService} from "../../_services/posts.service";

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
  @Output() likePost = new EventEmitter<number>();  // Declare an Output for the likePost event

  canReply: boolean = false;
  canEdit: boolean = false;
  canDelete: boolean = false;
  replyId: number | null = null;

  constructor(private postService: PostsService) {}

  ngOnInit(): void {
    const timeToEdit = 300000;
    const timePassed =
      new Date().getMilliseconds() -
      new Date(this.post.createdAt).getMilliseconds() > timeToEdit;

    this.canReply = Boolean(this.currentUserId);
    this.canEdit = this.currentUserId === this.post.userId && !timePassed;
    this.canDelete = this.currentUserId === this.post.userId && this.replies.length === 0;
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

  /*

 likePost(postId: number) {
  this.postService.likePost(postId).subscribe(
    response => {
      console.log('Post liked successfully', response);
      this.post.isLikedByCurrentUser = true;
    },
    error => {
      console.log('Error liking post', error);
    }
  );
}

<button [class.liked]="post.isLikedByCurrentUser" (click)="likePost(post.id)">
  Like
</button>


   */

}
