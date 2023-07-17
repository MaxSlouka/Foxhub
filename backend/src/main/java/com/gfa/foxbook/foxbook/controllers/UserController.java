package com.gfa.foxbook.foxbook.controllers;

import com.gfa.foxbook.foxbook.models.Comment;
import com.gfa.foxbook.foxbook.models.Post;
import com.gfa.foxbook.foxbook.models.User;
import com.gfa.foxbook.foxbook.models.dtos.UserBasicDTO;
import com.gfa.foxbook.foxbook.models.dtos.UserUpdateDTO;
import com.gfa.foxbook.foxbook.security.jwt.JwtUtils;
import com.gfa.foxbook.foxbook.services.PostService;
import com.gfa.foxbook.foxbook.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
        if (token == null) {
            return ResponseEntity.notFound().build();
        }
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


    @DeleteMapping("/people")
    public ResponseEntity<?> deletePerson(HttpServletRequest request){
        String token = jwtUtils.getJwtFromCookies(request);
        String email = jwtUtils.getUserNameFromJwtToken(token);
        Optional<User> maybeUser = userService.findByEmail(email);
        if (maybeUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User requestUser = maybeUser.get();

        userService.delete(requestUser);
        return ResponseEntity.noContent().build();
    }


    @PatchMapping("/people")
    public ResponseEntity<?> updateUserByNickname(HttpServletRequest request, @RequestBody UserUpdateDTO updateDTO) {
        String token = jwtUtils.getJwtFromCookies(request);
        String email = jwtUtils.getUserNameFromJwtToken(token);
        Optional<User> maybeUser = userService.findByEmail(email);
        if (maybeUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User requestUser = maybeUser.get();

        requestUser.setFirstName(updateDTO.getFirstName());
        requestUser.setLastName(updateDTO.getLastName());
        requestUser.setEmail(updateDTO.getEmail());

        requestUser.setGitHubURL(updateDTO.getGithub());
        requestUser.setLinkedInURL(updateDTO.getLinkedin());
        requestUser.setFacebookURL(updateDTO.getFacebook());
        requestUser.setInstagramURL(updateDTO.getInstagram());
        requestUser.setTwitterURL(updateDTO.getTwitter());

        userService.updateProfile(requestUser);

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
