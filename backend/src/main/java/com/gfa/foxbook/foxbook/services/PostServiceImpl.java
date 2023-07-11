package com.gfa.foxbook.foxbook.services;

import com.gfa.foxbook.foxbook.models.Post;
import com.gfa.foxbook.foxbook.repositories.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
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
    public List<Post> findAll() {
        assert PostRepository != null;
        return PostRepository.findAll();
    }

    @Override
    public List<Post> findAllByOrderByTimestampDesc() {
        assert PostRepository != null;
        return (List<Post>) PostRepository.findAllByOrderByTimestampDesc();
    }

    @Override
    public List<Post> findAllByOrderByTimestampAsc() {
        assert PostRepository != null;
        return (List<Post>) PostRepository.findAllByOrderByTimestampAsc();
    }

    @Override
    public List<Post> findAllByOrderByLikesDesc() {
        assert PostRepository != null;
        return (List<Post>) PostRepository.findAllByOrderByLikesDesc();
    }

        @Override
        public List<Post> findByUserName(String authorName){
            assert PostRepository != null;
            return (List<Post>) PostRepository.findByAuthor(authorName);
        }
    }

