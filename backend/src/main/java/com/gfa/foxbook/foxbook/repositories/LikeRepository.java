package com.gfa.foxbook.foxbook.repositories;

import com.gfa.foxbook.foxbook.models.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    void like(Long postId, Long userId);

    boolean voted(Long postId, Long userId);

    int countLikes(Long postId);
}
