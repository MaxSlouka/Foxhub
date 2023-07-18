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

  @Output() addPost = new EventEmitter<{text: string, parentPostId: number | null}>();

  // change all to false in deployment
  canReply: boolean = true;
  canEdit: boolean = true;
  canDelete: boolean = true;
  replyId: number | null = null;

  ngOnInit(): void {
    // this.canReply = Boolean(this.currentUserId);
    this.canReply = this.currentUserId === this.post.userId;
      this.canEdit = this.currentUserId === this.post.userId;
    // this.canDelete = this.currentUserId === this.post.userId && this.replies.length === 0;
    this.canDelete = this.currentUserId === this.post.userId;
    // this.replyId = this.parentPostId ? this.parentPostId : this.post.PostId;
  }

  isReplying(): Boolean {
    if (!this.activePost) {
      return false;
    }

    return this.activePost.PostId === this.post.PostId;
  }
}
