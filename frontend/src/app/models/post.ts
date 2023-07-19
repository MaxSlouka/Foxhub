export interface Post {
  id: number;
  content: string;
  author: string;
  userId: number;
  parentPostId: number | null;
  createdAt: string;
}
