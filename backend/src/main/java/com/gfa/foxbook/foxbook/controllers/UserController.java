package com.gfa.foxbook.foxbook.controllers;

import com.gfa.foxbook.foxbook.models.Comment;
import com.gfa.foxbook.foxbook.models.Post;
import com.gfa.foxbook.foxbook.models.User;
import com.gfa.foxbook.foxbook.models.dtos.UserBasicDTO;
import com.gfa.foxbook.foxbook.security.jwt.JwtUtils;
import com.gfa.foxbook.foxbook.services.PostService;
import com.gfa.foxbook.foxbook.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
public class UserController {
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final PostService postService;


    @GetMapping("/person")
    public ResponseEntity<?> getUser(HttpServletRequest request) {
        String token = jwtUtils.getJwtFromCookies(request);
        String email = jwtUtils.getUserNameFromJwtToken(token);
        Optional<User> maybeUser = userService.findByEmail(email);
        if (maybeUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User user = maybeUser.get();
        UserBasicDTO userBasicDTO = new UserBasicDTO();
        userBasicDTO.setFirstName(user.getFirstName());
        userBasicDTO.setLastName(user.getLastName());
        userBasicDTO.setEmail(user.getEmail());
        return ResponseEntity.ok(userBasicDTO);
        // todo recheck security holes
    }


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

//        if (!existingUser.getEmail().equals(principal.getName())) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not allowed to update this profile.");
//        } // todo recheck security holes

        userService.updateProfile(user);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<?> addComment(@PathVariable Long postId, @RequestBody String comment, Principal principal) {
        Optional<Post> maybePost = postService.findById(postId);

        if (maybePost.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Post post = maybePost.get();
        Optional<User> maybeUser = userService.findByEmail(principal.getName());

        if (maybeUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User user = maybeUser.get();

        Comment newComment = new Comment();
        newComment.setPost(post);
        newComment.setAuthor(user.getNickname());
        newComment.setContent(comment);

        postService.addComment(newComment);

        return ResponseEntity.ok().build();
    }


}
