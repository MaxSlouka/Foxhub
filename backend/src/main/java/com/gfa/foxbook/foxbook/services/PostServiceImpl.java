package com.gfa.foxbook.foxbook.services;

import com.gfa.foxbook.foxbook.models.Post;
import com.gfa.foxbook.foxbook.repositories.PostRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class PostServiceImpl implements PostService {

    private final PostRepository PostRepository;

    @Override
    public Post save(Post post) {
        return PostRepository.save(post);
    }

    @Override
    public Post findById(Long id) {
        return PostRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Post post) {
        PostRepository.delete(post);
    }

    @Override
    public Iterable<Post> findAll() {
        return PostRepository.findAll();
    }

    @Override
    public Iterable<Post> findAllByOrderByTimestampDesc() {
        return PostRepository.findAllByOrderByTimestampDesc();
    }

    @Override
    public Iterable<Post> findAllByOrderByTimestampAsc() {
        return PostRepository.findAllByOrderByTimestampAsc();
    }

    @Override
    public Iterable<Post> findAllByOrderByLikesDesc() {
        return PostRepository.findAllByOrderByLikesDesc();
    }

        @Override
        public Iterable<Post> findByUserName(String authorName){
            return PostRepository.findByUserName(authorName);
        }
    }

