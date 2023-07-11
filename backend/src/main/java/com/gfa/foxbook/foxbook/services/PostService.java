package com.gfa.foxbook.foxbook.services;

import com.gfa.foxbook.foxbook.models.Post;

import java.util.List;

public interface PostService {
    public Post save(Post post);

    public Post findById(Long id);

    public void delete(Post post);

    public List<Post> findAll();

    public List<Post> findAllByOrderByTimestampDesc();

    public List<Post> findAllByOrderByTimestampAsc();

    public List<Post> findAllByOrderByLikesDesc();

    public List<Post> findByUserName(String authorName);


}
