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

    }

    @Override
    public boolean voted(Long postId, Long userId) {
        return false;
    }

    @Override
    public int countLikes(Long postId) {
        return 0;
    }
}

