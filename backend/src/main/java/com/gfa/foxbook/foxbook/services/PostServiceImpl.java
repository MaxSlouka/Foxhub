package com.gfa.foxbook.foxbook.services;

import com.gfa.foxbook.foxbook.models.Post;
import com.gfa.foxbook.foxbook.repositories.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;


    @Override
    public Post save(Post post) {
        assert postRepository != null;
        return postRepository.save(post);
    }

    @Override
    public void delete(Post post) {
        assert postRepository != null;
        postRepository.delete(post);
    }

    @Override
    public List<Post> findAll() {
        assert postRepository != null;
        return postRepository.findAll();
    }

    @Override
    public List<Post> findAllByOrderByTimestampDesc() {
        assert postRepository != null;
        return (List<Post>) postRepository.findAllByOrderByTimestampDesc();
    }

    @Override
    public List<Post> findAllByOrderByTimestampAsc() {
        assert postRepository != null;
        return (List<Post>) postRepository.findAllByOrderByTimestampAsc();
    }

    @Override
    public List<Post> findAllByOrderByLikesDesc() {
        assert postRepository != null;
        return (List<Post>) postRepository.findAllByOrderByLikesDesc();
    }

        @Override
        public List<Post> findByUserName(String authorName){
            assert postRepository != null;
            return (List<Post>) postRepository.findByAuthor(authorName);
        }
    @Override
    public Post createPost(String author, String content, String title) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        Post p = new Post();
        p.setAuthor(author);
        p.setTitle(title);
        p.setContent(content);
        p.setTimestamp(Timestamp.valueOf(currentDateTime));
        return postRepository.save(p);
    }

    @Override
    public Post editPost(Long id, String title, String content) {
        Post p = postRepository.findById(id).orElse(null);
        assert p != null;
        p.setTitle(title);
        p.setContent(content);
        return postRepository.save(p);
    }

    @Override
    public Post editPost(Post post) {
        Post p = postRepository.findById(post.getId()).orElse(null);
        assert p != null;
        p = post;
        return postRepository.save(p);
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public void remove(Post post) {
        postRepository.delete(post);
    }


    }

