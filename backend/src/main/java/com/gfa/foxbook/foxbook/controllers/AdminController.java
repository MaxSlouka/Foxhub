package com.gfa.foxbook.foxbook.controllers;

import com.gfa.foxbook.foxbook.models.nonusermodels.Post;
import com.gfa.foxbook.foxbook.models.nonusermodels.Role;
import com.gfa.foxbook.foxbook.models.User;
import com.gfa.foxbook.foxbook.models.dtos.PostDTO;
import com.gfa.foxbook.foxbook.services.interfaces.PostService;
import com.gfa.foxbook.foxbook.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1/admin")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
public class AdminController {
    public final PostService postService;
    public final UserService userService;

    @PostMapping("/posts")
    public ResponseEntity<?> makePost(@RequestBody(required = false) Post post) {
        if (post == null) {
            return ResponseEntity.badRequest().build();
        }
        Post newPost = postService.createPost(post.getUsername(), post.getTitle(), post.getContent());
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/posts/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody PostDTO post) {
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
        user.getRoles().add(adminRole);
        //userService.updateProfile(user);
        // todo: fix this
        return ResponseEntity.ok().body(userService.upgradeUser(user.getNickname()));
    }
}