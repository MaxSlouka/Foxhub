package com.gfa.foxbook.foxbook.services;

import com.gfa.foxbook.foxbook.models.Post;
import com.gfa.foxbook.foxbook.repositories.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Post findById(Long id) {
        assert postRepository != null;
        return postRepository.findById(id).orElse(null);
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
    public Post createPost(String author, String content) {
        Post p = new Post();
        p.setAuthor(author);
        p.setContent(content);
        return postRepository.save(p);
    }

    @Override
    public Post createPost(Post post) {
        Post p = new Post();
        p.setAuthor(post.getAuthor());
        p.setContent(post.getContent());
        return postRepository.save(p);
    }

    @Override
    public Post findByID(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public void remove(Post post) {
        postRepository.delete(post);
    }
    }

