package com.gfa.foxbook.foxbook.controllers;

import com.gfa.foxbook.foxbook.models.User;
import com.gfa.foxbook.foxbook.models.dtos.EmailDTO;
import com.gfa.foxbook.foxbook.models.dtos.PasswordDTO;
import com.gfa.foxbook.foxbook.models.dtos.security.LoginDto;
import com.gfa.foxbook.foxbook.models.dtos.security.LoginResponseDto;
import com.gfa.foxbook.foxbook.models.dtos.security.RegisterDto;
import com.gfa.foxbook.foxbook.security.jwt.JwtUtils;
import com.gfa.foxbook.foxbook.security.services.SecurityService;
import com.gfa.foxbook.foxbook.services.EmailServiceImpl;
import com.gfa.foxbook.foxbook.services.interfaces.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final SecurityService securityService;
    private final UserService userService;
    private final EmailServiceImpl emailService;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            ResponseCookie jwtRefreshCookie = jwtUtils.generateRefreshJwtCookie(authentication);
            ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(authentication);
            if (!(userService.findByEmail(authentication.getName()).get().isVerified())){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You have to verify your email");
            }

            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                    .header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
                    .body(new LoginResponseDto(userService.findByEmail(authentication.getName())
                            .orElseThrow(() -> new IllegalStateException("User not found"))
                            .getNickname()));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid login credentials");
        }
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) throws MessagingException {
        if (securityService.userExistsByEmail(registerDto.getEmail())) {
            return ResponseEntity.badRequest().body("Email already registered");
        }
        securityService.registerUser(registerDto);
        emailService.send(registerDto.getEmail(), "Welcome to Foxhub", emailService.generateWelcomeEmail(registerDto.getFirstName()));
        emailService.send(registerDto.getEmail(), "Foxhub - Email verification", emailService.generateVerificationEmail(userService.findByEmail(registerDto.getEmail()).get().getVerificationToken()));

        return ResponseEntity.ok().build();
    }

    @PostMapping("signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie jwtCookie = jwtUtils.getCleanJwtCookie();
        ResponseCookie jwtRefreshCookie = jwtUtils.getCleanJwtRefreshCookie();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
                .build();
    }

    @PostMapping("refresh")
    public ResponseEntity<?> refreshToken(HttpServletRequest request) {
        if (jwtUtils.getRefreshTokenValidateAndGenerateAccessToken(request) != null) {
            ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(jwtUtils.getRefreshTokenValidateAndGenerateAccessToken(request));

            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).build();
        }
        return ResponseEntity.badRequest().body("Refresh token is expired");
    }

    @PostMapping("password-reset")
    public ResponseEntity<?> resetPassword(@RequestBody EmailDTO emailDTO) throws MessagingException {
        Optional<User> user = userService.findByEmailAndYearOfBirth(emailDTO.getEmail(), emailDTO.getYearOfBirth());
        if (user.isEmpty()){
            return ResponseEntity.badRequest().body("user does not exist");
        }

        if (!(user.get().getEmail().equals(emailDTO.getEmail()) && user.get().getYearOfBirth() == emailDTO.getYearOfBirth())) {
            return ResponseEntity.badRequest().body("given user info is not valid");
        }

        String newPassword = UUID.randomUUID().toString();
        user.get().setPassword(passwordEncoder.encode(newPassword));

        userService.saveUser(user.get());

        emailService.send(emailDTO.getEmail(),"Foxhub - Password reset", "You have reseted your password. Your new password is: "+newPassword+" \n Please change in after login");
        return ResponseEntity.ok().build();
    }


    @GetMapping("verify-email/{token}")
    public ResponseEntity<?> verify(@PathVariable String token) {
        User user = userService.getUserByVerificationToken(token);

        if (user != null && user.getVerificationToken().equals(token)) {
            user.setVerified(true);
            userService.saveUser(user);
            return ResponseEntity.ok("Email verification successful <a href=\"http://localhost:4200/login\">continue to login<a/>");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid verification token");
    }

}
