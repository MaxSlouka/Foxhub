package com.gfa.foxbook.foxbook.services;

import com.gfa.foxbook.foxbook.models.Like;
import com.gfa.foxbook.foxbook.models.Post;
import com.gfa.foxbook.foxbook.models.User;
import com.gfa.foxbook.foxbook.repositories.LikeRepository;
import com.gfa.foxbook.foxbook.repositories.PostRepository;
import com.gfa.foxbook.foxbook.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    public void likePost(Long postId, Long userId) {
        assert likeRepository != null;
        Like like = likeRepository.findByPostIdAndUserId(postId, userId);
        if (like != null) {
            return;
        }

        Optional<Post> maybePost = postRepository.findById(postId);
        Optional<User> maybeUser = userRepository.findById(userId);

        if (maybePost.isPresent() && maybeUser.isPresent()) {
            Post post = maybePost.get();
            User user = maybeUser.get();

            like = new Like();
            like.setPost(post);
            like.setUserId(user.getId());
            like.setLikes(0);
            like.setHasVoted(false);

            likeRepository.save(like);
        }
    }

    @Override
    public void like(Like like) {
        assert likeRepository != null;
        likeRepository.save(like);
    }


    @Override
    public void like(Long postId, Long userId) {
        assert likeRepository != null;
        Like like = likeRepository.findByPostIdAndUserId(postId, userId);
        if (like != null) {
            return;
        }

        like = new Like();
        like.setUserId(userId);
        like.setLikes(0);
        like.setHasVoted(false);

        likeRepository.save(like);
    }

    @Override
    public boolean hasUserLikedPost(Long postId, Long userId) {
        assert likeRepository != null;
        Like like = likeRepository.findByPostIdAndUserId(postId, userId);
        return like != null;
    }

    @Override
    public void save(Like like) {
        assert likeRepository != null;
        likeRepository.save(like);
    }

    @Override
    public boolean hasUserLiked(Long commentId, Long userId) {
        return false;
    }

    @Override
    public User getUser(Long userId) {
        Optional<User> maybeUser = userRepository.findById(userId);

        if (maybeUser.isPresent()) {
            User user = maybeUser.get();
            return user;
        }
        return null;
    }
}
