package com.gfa.foxbook.foxbook.controllers;

import com.gfa.foxbook.foxbook.models.User;
import com.gfa.foxbook.foxbook.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;

    @DeleteMapping("/people/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Long id, Principal principal) {
        Optional<User> user = userService.findById(id);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User existingUser = user.get();

        if (!existingUser.getEmail().equals(principal.getName())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not allowed to delete this profile.");
        }

        userService.delete(existingUser);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/people/{nickname}")
    public ResponseEntity<?> updateUserByNickname(@RequestBody User user, @PathVariable String nickname, Principal principal) {
        Optional<User> maybeUser = userService.findByNickname(nickname);

        if (maybeUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User existingUser = maybeUser.get();

        if (!existingUser.getEmail().equals(principal.getName())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not allowed to update this profile.");
        }

        userService.updateProfile(user);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/people/comments")
    public ResponseEntity<?> addComment(@RequestBody String comment, Principal principal) {
        Optional<User> maybeUser = userService.findByEmail(principal.getName());

        if (maybeUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User existingUser = maybeUser.get();

        userService.addComment(existingUser, comment);

        return ResponseEntity.ok().build();
    }

}
