package com.gfa.foxbook.foxbook.services;

import com.gfa.foxbook.foxbook.models.nonusermodels.Comment;
import com.gfa.foxbook.foxbook.repositories.CommentRepository;
import com.gfa.foxbook.foxbook.services.interfaces.CommentService;
import com.gfa.foxbook.foxbook.services.interfaces.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final LikeService likeService;

    @Override
    public void comment(Long postId, Long userId, String comment) {
        Comment c = new Comment();
        c.setPostId(String.valueOf(postId));
        c.setAuthor(c.getAuthor());
        c.setContent(comment);
        c.setTimestamp(new Date(Timestamp.valueOf(LocalDateTime.now()).getTime()));
        commentRepository.save(c);
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public void editComment(Long commentId, String comment) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isPresent()) {
            Comment existingComment = optionalComment.get();
            existingComment.setContent(comment);
            commentRepository.save(existingComment);
        }
    }

    @Override
    public void likeComment(Long commentId, Long userId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            boolean hasLiked = likeService.hasUserLiked(commentId, userId);
            if (!hasLiked) {
                likeService.like(commentId, userId);
            }
            commentRepository.save(comment);
        }
    }
}
