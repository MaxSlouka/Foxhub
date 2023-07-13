package com.gfa.foxbook.foxbook.services;

import com.gfa.foxbook.foxbook.models.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Optional<Post> findById(Long id);
    public Post save(Post post);

    public void delete(Post post);

    public List<Post> findAll();

    public List<Post> findAllByOrderByTimestampDesc();

    public List<Post> findAllByOrderByTimestampAsc();

    public List<Post> findAllByOrderByLikesDesc();

    public List<Post> findByUserName(String authorName);

    void remove(Post post);

    Post createPost(String author, String title, String content);

    Post editPost(Long id, String title, String content);

    Post editPost(Post post);
}
