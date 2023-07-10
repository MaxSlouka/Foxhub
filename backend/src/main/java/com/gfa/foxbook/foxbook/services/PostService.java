package com.gfa.foxbook.foxbook.services;

import com.gfa.foxbook.foxbook.models.Post;

public interface PostService {
    public Post save(Post post);

    public Post findById(Long id);

    public void delete(Post post);

    public Iterable<Post> findAll();

    public Iterable<Post> findAllByOrderByTimestampDesc();

    public Iterable<Post> findAllByOrderByTimestampAsc();

    public Iterable<Post> findAllByOrderByLikesDesc();

    public Iterable<Post> findByUserName(String authorName);


}
