package com.gfa.foxbook.foxbook.controllers;

import com.gfa.foxbook.foxbook.models.User;
import com.gfa.foxbook.foxbook.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ApiController {

    private final UserService userService;

    @GetMapping("/person/{id}")
    public ResponseEntity<?> personDetails(@PathVariable Long id) {
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User user = optionalUser.get();
        return ResponseEntity.ok(user);
    }


    @DeleteMapping("/people/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        userService.delete(user.get());
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/people")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @PutMapping("/people/{nickname}")
    public ResponseEntity<?> updateUserByNickname(@RequestBody User user, @PathVariable String nickname) {
        Optional<User> maybeUser = userService.findByNickname(nickname);

        if (maybeUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        userService.updateProfile(user);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/posts")
    public ResponseEntity<?> getAllPosts() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<?> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/posts")
    public ResponseEntity<?> createPost(@RequestBody Object object) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody Object object) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }
}
