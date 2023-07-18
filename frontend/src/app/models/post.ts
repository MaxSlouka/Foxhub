
export class Post {
  PostId?: number;
  content!: string;
  author?: string;
  userId?: number;
  parentPostId: number | null = null;
  createdAt?: string;
}
