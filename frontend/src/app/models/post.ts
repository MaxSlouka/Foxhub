export interface Post {
  id: number;
  content: string;
  username: string;
  userId: number;
  parentPostId: number | null;
  createdAt: string;
}
