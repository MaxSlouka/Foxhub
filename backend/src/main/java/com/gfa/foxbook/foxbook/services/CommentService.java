package com.gfa.foxbook.foxbook.services;

public interface CommentService {

    public void comment(Long postId, Long userId, String comment);

    public void deleteComment(Long commentId);

    public void editComment(Long commentId, String comment);

    public void likeComment(Long commentId, Long userId);

}
