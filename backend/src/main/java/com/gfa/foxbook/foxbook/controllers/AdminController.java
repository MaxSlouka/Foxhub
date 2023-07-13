package com.gfa.foxbook.foxbook.controllers;

import com.gfa.foxbook.foxbook.models.Post;
import com.gfa.foxbook.foxbook.models.Role;
import com.gfa.foxbook.foxbook.models.User;
import com.gfa.foxbook.foxbook.services.PostService;
import com.gfa.foxbook.foxbook.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1/admin")
public class AdminController {
    public final PostService postService;
    public final UserService userService;

    @GetMapping("/posts/{id}")
    public ResponseEntity<?> getPostById(@PathVariable Long id) {
        Optional<Post> optionalPost = postService.findById(id);
        if (optionalPost.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Post post = optionalPost.get();
        return ResponseEntity.ok(post);
    }

    @PostMapping("/posts")
    public ResponseEntity<?> makePost(@RequestBody(required = false) Post post) {
        if (post == null) {
            return ResponseEntity.badRequest().body("You have to write something!");
        }
        Post newPost = postService.createPost(post.getAuthor(), post.getTitle(), post.getContent());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newPost.getId())
                .toUri();
        return ResponseEntity.created(location).body(newPost);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody Post post) {
        Optional<Post> optionalPost = postService.findById(id);
        if (optionalPost.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Post existingPost = optionalPost.get();
        existingPost.setTitle(post.getTitle());
        existingPost.setContent(post.getContent());
        Post updatedPost = postService.editPost(existingPost);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        Optional<Post> optionalPost = postService.findById(id);
        if (optionalPost.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Post post = optionalPost.get();
        postService.remove(post);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/upgrade/{nickname}")
    public ResponseEntity<?> upgradeUser(@PathVariable String nickname) {
        Optional<User> maybeUser = userService.findByNickname(nickname);
        if (maybeUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User user = maybeUser.get();
        Role adminRole = new Role("ADMIN");
        user.setRoles(Collections.singletonList(adminRole));
        userService.updateProfile(user);
        return ResponseEntity.ok().body(userService.upgradeUser(user.getNickname()));
    }
}