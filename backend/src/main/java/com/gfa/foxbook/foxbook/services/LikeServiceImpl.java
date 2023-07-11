package com.gfa.foxbook.foxbook.services;

import com.gfa.foxbook.foxbook.models.Like;
import com.gfa.foxbook.foxbook.repositories.LikeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;

    @Override
    public void like(Long postId, Long userId) {
        assert likeRepository != null;
        Like like = likeRepository.findByPostIdAndUserId(postId, userId);
        if (like != null) {
            return;
        }

        like = new Like();
        like.setPostId(postId);
        like.setUserId(userId);
        like.setLikes(1);
        like.setHasVoted(true);

        likeRepository.save(like);
    }

    @Override
    public boolean hasUserLiked(Long postId, Long userId) {
        assert likeRepository != null;
        Like like = likeRepository.findByPostIdAndUserId(postId, userId);
        return like != null;
    }
}
