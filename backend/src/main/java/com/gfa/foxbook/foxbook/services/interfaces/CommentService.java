package com.gfa.foxbook.foxbook.services.interfaces;

import com.gfa.foxbook.foxbook.models.User;
import com.gfa.foxbook.foxbook.models.nonusermodels.Comment;
import com.gfa.foxbook.foxbook.models.nonusermodels.Post;

public interface CommentService {
    void saveComment(Comment comment);

    Comment createComment(User user, Post post, String content);

    void save(Comment comment);
}
