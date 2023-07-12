package com.gfa.foxbook.foxbook.controllers;

import com.gfa.foxbook.foxbook.models.User;
import com.gfa.foxbook.foxbook.services.PostService;
import com.gfa.foxbook.foxbook.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1/public")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
public class PublicController {
    private final UserService userService;
    private final PostService postService;

    @GetMapping("/people")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok().body(userService.findAll());
    }
    @GetMapping("/person/{nickname}")
    public ResponseEntity<?> personDetails(@PathVariable String nickname) {
        Optional<User> maybeUser = userService.findByNickname(nickname);
        if (maybeUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User user = maybeUser.get();
        return ResponseEntity.ok(user);
    }
    @GetMapping("/posts")
    public ResponseEntity<?> getAllPosts() {
        return postService.findAll().isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(postService.findAll());
    }

}
