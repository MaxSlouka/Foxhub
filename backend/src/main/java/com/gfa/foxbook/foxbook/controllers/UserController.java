package com.gfa.foxbook.foxbook.controllers;

import com.gfa.foxbook.foxbook.models.dtos.PasswordDTO;
import com.gfa.foxbook.foxbook.models.dtos.UserProfileDTO;
import com.gfa.foxbook.foxbook.models.nonusermodels.Comment;
import com.gfa.foxbook.foxbook.models.nonusermodels.Like;
import com.gfa.foxbook.foxbook.models.nonusermodels.Post;
import com.gfa.foxbook.foxbook.models.User;
import com.gfa.foxbook.foxbook.security.jwt.JwtUtils;
import com.gfa.foxbook.foxbook.services.interfaces.CommentService;
import com.gfa.foxbook.foxbook.services.interfaces.LikeService;
import com.gfa.foxbook.foxbook.services.interfaces.PostService;
import com.gfa.foxbook.foxbook.services.interfaces.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
public class UserController {
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final PostService postService;
    private final LikeService likeService;
    private final PasswordEncoder passwordEncoder;
    private final CommentService commentService;


    private String uploadDir = "./uploads";

    @GetMapping("/person")
    public ResponseEntity<?> getUser(HttpServletRequest request) {
        User user = jwtUtils.getUserFromRequest(request);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        UserProfileDTO userDTO = new UserProfileDTO(user);
        System.out.println(userDTO.isWorkStatus());
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("/people")
    public ResponseEntity<?> deletePerson(HttpServletRequest request) {
        User user = jwtUtils.getUserFromRequest(request);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        userService.delete(user);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/people")
    public ResponseEntity<?> updateUserByNickname(HttpServletRequest request, @RequestBody User updateDTO) {
        User requestUser = jwtUtils.getUserFromRequest(request);
        if (requestUser == null || updateDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        userService.updateProfile(requestUser, updateDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        User user = jwtUtils.getUserFromRequest(request);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        String nickname = user.getNickname();
        String fileName = file.getOriginalFilename();
        String extension = fileName.substring(fileName.lastIndexOf("."));
        System.out.println(extension);
        // todo extention
        try {
            Files.createDirectories(Paths.get(uploadDir));
            Path filePath = Paths.get(uploadDir, nickname + extension);
            file.transferTo(filePath);
            user.setProfilePictureUrl("http://localhost:8080/uploads/"+nickname+ extension);
            userService.saveUser(user);


            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload the file: " + e.getMessage());
        }
    }

    @PostMapping("/posts/{postId}/like")
    public ResponseEntity<?> likePost(@PathVariable Long postId, HttpServletRequest request) {
        String token = jwtUtils.getJwtFromCookies(request);
        String email = jwtUtils.getUserNameFromJwtToken(token);
        Optional<User> maybeUser = userService.findByEmail(email);

        if (maybeUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Post> maybePost = postService.findById(postId);

        if (maybePost.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User user = maybeUser.get();
        Post post = maybePost.get();

        if (likeService.hasUserLikedPost(postId, user.getId())) {
            return ResponseEntity.badRequest().body("You already liked this post.");
        }

        Like like = new Like(post, user.getId(), 0, false);
        likeService.like(like);
        like.getPost().setLikesCount(like.getPost().getLikesCount() + 1);

        postService.save(like.getPost());
        return ResponseEntity.ok().build();
    }
    @PostMapping("password-change")
    public ResponseEntity<?> changePassword(@RequestBody PasswordDTO passwordDTO, HttpServletRequest request){
        User user = jwtUtils.getUserFromRequest(request);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        user.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
        userService.saveUser(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("post/{postId}/comment")
    public ResponseEntity<?> commentPost(@PathVariable Long postId, @RequestBody Comment comment, HttpServletRequest request) {
        User user = jwtUtils.getUserFromRequest(request);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Post> maybePost = postService.findById(postId);
        if (maybePost.isEmpty()) {
            return new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);
        }

        try {
            Post post = maybePost.get();
            comment.setUserId(user.getId());
            comment.setUsername(user.getNickname());
            post.addComment(comment);
            commentService.saveComment(comment);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while saving the comment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
