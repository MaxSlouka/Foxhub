export class CreatePostPayload {
  title!: string;
  url?: string;
  author?: string;
  authorPic?: string;
  content!: string;
  timestamp?: string;
  comments?: string[];
}
