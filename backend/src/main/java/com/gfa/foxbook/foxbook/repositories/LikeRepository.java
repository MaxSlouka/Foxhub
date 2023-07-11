package com.gfa.foxbook.foxbook.repositories;

import com.gfa.foxbook.foxbook.models.Like;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LikeRepository extends JpaRepository<Like, Long> {
    Like findByPostIdAndUserId(Long postId, Long userId);
}
