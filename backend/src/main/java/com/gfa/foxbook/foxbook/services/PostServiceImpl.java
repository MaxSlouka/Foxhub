package com.gfa.foxbook.foxbook.services;

import com.gfa.foxbook.foxbook.models.nonusermodels.Comment;
import com.gfa.foxbook.foxbook.models.nonusermodels.Post;
import com.gfa.foxbook.foxbook.repositories.PostRepository;
import com.gfa.foxbook.foxbook.services.interfaces.PostService;
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
        return postRepository.save(post);
    }

    @Override
    public void delete(Post post) {
        postRepository.delete(post);
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> findAllByOrderByTimestampDesc() {
        return postRepository.findAllByOrderByTimestampDesc();
    }

    @Override
    public List<Post> findAllByOrderByTimestampAsc() {
        return postRepository.findAllByOrderByTimestampAsc();
    }

    @Override
    public List<Post> findAllByOrderByLikesDesc() {
        return postRepository.findAllByOrderByLikesDesc();
    }

    @Override
    public List<Post> findByUserName(String authorName) {
        return postRepository.findByAuthor(authorName);
    }

    @Override
    public Post createPost(String author, String content, String title) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        Post p = new Post();
        p.setUsername(author);
        p.setTitle(title);
        p.setContent(content);
        p.setCreatedAt(Timestamp.valueOf(currentDateTime));
        return postRepository.save(p);
    }

    @Override
    public Post editPost(Long id, String title, String content) {
        Post p = postRepository.findById(id).get();
        p.setTitle(title);
        p.setContent(content);
        p.setId(id);
        return postRepository.save(p);
    }

    @Override
    public Post editPost(Post post) {
        Optional<Post> optionalPost = postRepository.findById(post.getId());

        if (optionalPost.isPresent()) {
            Post existingPost = optionalPost.get();
            existingPost.setUsername(post.getUsername());
            existingPost.setTitle(post.getTitle());
            existingPost.setContent(post.getContent());

            return postRepository.save(existingPost);
        } else {
            throw new IllegalArgumentException("Post not found");
        }
    }

    @Override
    public void addComment(Comment newComment) {
        Post p = postRepository.findById(Long.valueOf(newComment.getPostId())).get();
        p.addComment(newComment);
        postRepository.save(p);
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

