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
        assert PostRepository != null;
        return PostRepository.save(post);
    }

    @Override
    public Post findById(Long id) {
        assert PostRepository != null;
        return PostRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Post post) {
        assert PostRepository != null;
        PostRepository.delete(post);
    }

    @Override
    public Iterable<Post> findAll() {
        assert PostRepository != null;
        return PostRepository.findAll();
    }

    @Override
    public Iterable<Post> findAllByOrderByTimestampDesc() {
        assert PostRepository != null;
        return PostRepository.findAllByOrderByTimestampDesc();
    }

    @Override
    public Iterable<Post> findAllByOrderByTimestampAsc() {
        assert PostRepository != null;
        return PostRepository.findAllByOrderByTimestampAsc();
    }

    @Override
    public Iterable<Post> findAllByOrderByLikesDesc() {
        assert PostRepository != null;
        return PostRepository.findAllByOrderByLikesDesc();
    }

        @Override
        public Iterable<Post> findByUserName(String authorName){
            assert PostRepository != null;
            return PostRepository.findByUserName(authorName);
        }
    }

