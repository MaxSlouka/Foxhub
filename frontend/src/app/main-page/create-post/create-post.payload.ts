export class CreatePostPayload {
  title!: string;
  author?: string;
  authorPic?: string;
  content!: string;
  timestamp?: string;
  like?: number;
  dislike?: number;
  comments?: string[];
}
