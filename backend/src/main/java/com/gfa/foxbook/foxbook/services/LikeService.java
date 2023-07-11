package com.gfa.foxbook.foxbook.services;

public interface LikeService {
    void like(Long postId, Long userId);
    boolean hasUserLiked(Long postId, Long userId);
}
