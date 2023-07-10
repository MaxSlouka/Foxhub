package com.gfa.foxbook.foxbook.controllers;


import com.gfa.foxbook.foxbook.models.Role;
import com.gfa.foxbook.foxbook.models.User;
import com.gfa.foxbook.foxbook.models.dtos.ResponseDTO;
import com.gfa.foxbook.foxbook.models.dtos.security.AuthResponseDTO;
import com.gfa.foxbook.foxbook.models.dtos.security.LoginDto;
import com.gfa.foxbook.foxbook.models.dtos.security.RegisterDto;
import com.gfa.foxbook.foxbook.security.JWTGenerator;
import com.gfa.foxbook.foxbook.repositories.RoleRepository;
import com.gfa.foxbook.foxbook.repositories.UserRepository;
import com.gfa.foxbook.foxbook.services.SecurityService;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
//@CrossOrigin(origins = "*",maxAge = 3600, allowCredentials = "true")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JWTGenerator jwtGenerator;
    private final SecurityService securityService;


    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);


        // Create a new cookie
        String cookieName = "token";
        String cookieValue = token;

        // Set the cookie's path (optional)
        String cookiePath = "/";

        // Set the cookie's max age (optional)
        int maxAge = 3600; // 1 hour

        // Create the cookie header value
        String cookieHeaderValue = cookieName + "=" + cookieValue + "; Path=" + cookiePath + "; Max-Age=" + maxAge+ "; HttpOnly";

        // Create the HttpHeaders object and set the cookie header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Set-Cookie", cookieHeaderValue);

        return ResponseEntity.ok().headers(headers).build();
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        if (securityService.userExistsByEmail(registerDto.getEmail())) {
            return ResponseEntity.badRequest().body("Email already registered");
        }
        securityService.registerUser(registerDto);
        return ResponseEntity.ok(new ResponseDTO("User registered successfully"));
    }


}
