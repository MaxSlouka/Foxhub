package com.gfa.foxbook.foxbook.controllers;

import com.gfa.foxbook.foxbook.models.dtos.UserProfileDTO;
import com.gfa.foxbook.foxbook.models.nonusermodels.Like;
import com.gfa.foxbook.foxbook.models.nonusermodels.Post;
import com.gfa.foxbook.foxbook.models.User;
import com.gfa.foxbook.foxbook.security.jwt.JwtUtils;
import com.gfa.foxbook.foxbook.services.interfaces.LikeService;
import com.gfa.foxbook.foxbook.services.interfaces.PostService;
import com.gfa.foxbook.foxbook.services.interfaces.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private String uploadDir = "./uploads";

    @GetMapping("/person")
    public ResponseEntity<?> getUser(HttpServletRequest request) {
        User user = jwtUtils.getUserFromRequest(request);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        UserProfileDTO userDTO = new UserProfileDTO(user);
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
        String extension = file.getContentType();
        System.out.println(extension);
        // todo extention
        try {
            Files.createDirectories(Paths.get(uploadDir));
            Path filePath = Paths.get(uploadDir, nickname + ".jpg");
            file.transferTo(filePath);
            user.setProfilePictureUrl("http://localhost:8080/uploads/"+nickname+".jpg");
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

        return ResponseEntity.ok().build();
    }
}
