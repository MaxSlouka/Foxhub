package com.gfa.foxbook.foxbook.controllers;

import com.gfa.foxbook.foxbook.models.dtos.UserSearchDTO;
import com.gfa.foxbook.foxbook.models.nonusermodels.Post;
import com.gfa.foxbook.foxbook.models.User;
import com.gfa.foxbook.foxbook.services.interfaces.PostService;
import com.gfa.foxbook.foxbook.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1/public")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
public class PublicController {
    private final UserService userService;
    private final PostService postService;

    @GetMapping("/people")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userService.getAll();
        List<UserSearchDTO> usersDTO = new ArrayList<>();
        for (User user : users) {
            usersDTO.add(new UserSearchDTO(user));
        }
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usersDTO);
    }

    @GetMapping("/people/{nickname}")
    public ResponseEntity<?> personDetails(@PathVariable String nickname) {
        Optional<User> maybeUser = userService.findByNickname(nickname);
        if (maybeUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User user = maybeUser.get();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<?> getPostById(@PathVariable Long id) {
        Optional<Post> optionalPost = postService.findById(id);
        if (optionalPost.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Post post = optionalPost.get();
        return ResponseEntity.ok(post);
    }

    @GetMapping("/posts")
    public ResponseEntity<?> getAllPosts() {
        List<Post> posts = postService.findAllByOrderByTimestampDesc();
        if (posts.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(posts);
    }

}
