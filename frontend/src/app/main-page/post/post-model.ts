export class PostModel {
  id?: number;
  title!: string;
  url?: string;
  author?: string;
  authorPic?: string;
  content!: string;
  timestamp?: string;
  comments?: string[];
  commentsCount?: number;
}
