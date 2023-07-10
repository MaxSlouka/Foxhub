export class PostModel {
  id?: number;
  title!: string;
  author?: string;
  authorPic?: string;
  content!: string;
  timestamp?: string;
  comments?: string[];
  commentsCount?: number;
}
