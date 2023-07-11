package com.gfa.foxbook.foxbook.controllers;

import com.gfa.foxbook.foxbook.models.Post;
import com.gfa.foxbook.foxbook.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1/admin")
public class AdminController {
    public final PostService postService;
    @GetMapping("/posts/{id}")
    public ResponseEntity<?> getPostById(@PathVariable Long id) {
        return postService.findById(id).isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok().build();
    }

    @PostMapping("/posts")
    public ResponseEntity<?> makePost(@RequestBody(required = false) Post post) {
        if (post == null) {
            return ResponseEntity.badRequest().body("You have to write something!");
        }
        Post newPost = postService.createPost(post.getAuthor(), post.getContent());
        return ResponseEntity.ok().body(newPost);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody Post post) {
        return postService.findById(id).isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok().build();
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        Post post = postService.findByID(id);
        postService.remove(post);
        return ResponseEntity.status(200).body(post);
    }

}
