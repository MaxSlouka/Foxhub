package com.gfa.foxbook.foxbook.repositories;

import com.gfa.foxbook.foxbook.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Iterable<Post> findAllByOrderByTimestampDesc();

    Iterable<Post> findAllByOrderByTimestampAsc();

    Iterable<Post> findAllByOrderByLikesDesc();

    Iterable<Post> findByAuthor(String authorName);
}
