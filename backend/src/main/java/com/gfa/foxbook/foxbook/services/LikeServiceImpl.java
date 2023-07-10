package com.gfa.foxbook.foxbook.services;

import com.gfa.foxbook.foxbook.repositories.LikeRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class LikeServiceImpl  implements LikeService{

    private final LikeRepository likeRepository;

    @Override
    public void like(Long postId, Long userId) {
        likeRepository.like(postId, userId);
    }

    @Override
    public boolean voted(Long postId, Long userId) {
        return likeRepository.voted(postId, userId);
    }

    @Override
    public int countLikes(Long postId) {
        return likeRepository.countLikes(postId);
    }
}
