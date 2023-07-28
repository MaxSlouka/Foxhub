package com.gfa.foxbook.foxbook.controllers;

import com.gfa.foxbook.foxbook.models.nonusermodels.Post;
import com.gfa.foxbook.foxbook.models.nonusermodels.Role;
import com.gfa.foxbook.foxbook.models.User;
import com.gfa.foxbook.foxbook.models.dtos.PostDTO;
import com.gfa.foxbook.foxbook.security.jwt.JwtUtils;
import com.gfa.foxbook.foxbook.services.interfaces.PostService;
import com.gfa.foxbook.foxbook.services.interfaces.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1/admin")
@CrossOrigin(origins = {"http://localhost:4200", "http://foxhub.gfapp.eu"}, maxAge = 3600, allowCredentials = "true")
public class AdminController {
    public final PostService postService;
    private final JwtUtils jwtUtils;
    public final UserService userService;

    @PostMapping("/posts")
    public ResponseEntity<?> makePost(@RequestBody Post post, HttpServletRequest request) {
        User user = jwtUtils.getUserFromRequest(request);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        post.setAuthorProfilePic(user.getProfilePictureUrl());
        return ResponseEntity.ok(postService.save(post));
    }

    @PatchMapping("/posts/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody PostDTO post) {
        Optional<Post> optionalPost = postService.findById(id);

        if (optionalPost.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        postService.editPost(optionalPost.get().getId(), post.getContent());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        Optional<Post> optionalPost = postService.findById(id);
        if (optionalPost.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Post post = optionalPost.get();
        postService.delete(post);
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

    @DeleteMapping("/people/{nickname}")
    public ResponseEntity<?> deleteUser(@PathVariable String nickname) {
        Optional<User> maybeUser = userService.findByNickname(nickname);
        if (maybeUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User user = maybeUser.get();
        userService.delete(user);
        return ResponseEntity.noContent().build();
    }
}