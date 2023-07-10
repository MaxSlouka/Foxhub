export class CreatePostPayload {
  title!: string;
  author?: string;
  authorPic?: string;
  content!: string;
  timestamp?: string;
  comments?: string[];
}
