package com.gfa.foxbook.foxbook.services;

public interface LikeService {

    public void like(Long postId, Long userId);

    public boolean voted(Long postId, Long userId);

    public int countLikes(Long postId);


}
