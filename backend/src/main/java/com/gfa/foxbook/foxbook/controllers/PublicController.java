package com.gfa.foxbook.foxbook.controllers;

import com.gfa.foxbook.foxbook.models.dtos.UserProfileDTO;
import com.gfa.foxbook.foxbook.models.dtos.UserSearchDTO;
import com.gfa.foxbook.foxbook.models.nonusermodels.Post;
import com.gfa.foxbook.foxbook.models.User;
import com.gfa.foxbook.foxbook.services.interfaces.PersonalityService;
import com.gfa.foxbook.foxbook.services.interfaces.PostService;
import com.gfa.foxbook.foxbook.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    private final PersonalityService personalityService;

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
        UserProfileDTO userDTO = new UserProfileDTO(user);
        return ResponseEntity.ok(userDTO);
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

    @GetMapping("/people/search")
    public ResponseEntity<?> searchUsers(@RequestParam("query") String query) {
        List<User> users = userService.searchUsers(query);
        List<UserSearchDTO> usersDTO = new ArrayList<>();
        for (User user : users) {
            usersDTO.add(new UserSearchDTO(user));
        }
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usersDTO);
    }

    @GetMapping("/personalities")
    public ResponseEntity<?> getAllPersonalities() {
        return ResponseEntity.ok(personalityService.getAllPersonalities());
    }

    @GetMapping("/posts/comments/{id}")
    public ResponseEntity<?> getPostComments(@PathVariable Long id) {
        Optional<Post> optionalPost = postService.findById(id);
        if (optionalPost.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Post post = optionalPost.get();
        return ResponseEntity.ok(post.getComments());

    }
}
