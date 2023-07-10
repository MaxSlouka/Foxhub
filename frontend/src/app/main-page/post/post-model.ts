export class PostModel {
  id?: number;
  title!: string;
  author?: string;
  authorPic?: string;
  content!: string;
  timestamp?: string;
  like?: number;
  dislike?: number;
  upVote?: boolean;
  downVote?: boolean;
  comments?: string[];
  commentsCount?: number;
}
