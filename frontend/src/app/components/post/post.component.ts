import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Post} from "../../models/post";

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {
  @Input() post!: Post;
  @Input() currentUserId!: number;
  @Input() replies!: Post[];
  @Input() activePost!: Post | null;
  @Input() parentPostId!: number | null;

  @Output() setActivePost = new EventEmitter<Post | null>();
  @Output() addPost = new EventEmitter<{
    text: string, parentPostId: number | null
  }>();
  @Output() updatePost = new EventEmitter<{
    text: string, id: number
  }>();
  @Output() deletePost = new EventEmitter<number>();

  canReply: boolean = false;
  canEdit: boolean = false;
  canDelete: boolean = false;
  replyId: number | null = null;

  ngOnInit(): void {
    const timeToEdit = 300000;
    const timePassed =
      new Date().getMilliseconds() -
      new Date(this.post.createdAt).getMilliseconds() > timeToEdit;
    this.canReply = Boolean(this.currentUserId);
      this.canEdit = this.currentUserId === this.post.userId;
    this.canDelete = this.currentUserId === this.post.userId && this.replies.length === 0;
    // this.replyId = this.parentPostId ? this.parentPostId : this.post.PostId;
  }

  isReplying(): Boolean {
    if (!this.activePost) {
      return false;
    }

    return this.activePost.id === this.post.id;
  }
}
