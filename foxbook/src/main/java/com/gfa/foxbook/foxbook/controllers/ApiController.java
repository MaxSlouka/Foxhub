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

    @GetMapping("/people/{id}")
    public ResponseEntity<?> personDetails(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        return ResponseEntity.ok().body(user);
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
}
