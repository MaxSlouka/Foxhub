package com.gfa.foxbook.foxbook.controllers;

import com.gfa.foxbook.foxbook.models.dtos.ResponseDTO;
import com.gfa.foxbook.foxbook.models.dtos.security.LoginDto;
import com.gfa.foxbook.foxbook.models.dtos.security.RegisterDto;
import com.gfa.foxbook.foxbook.security.jwt.JwtUtils;
import com.gfa.foxbook.foxbook.services.SecurityService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final SecurityService securityService;


    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);


        ResponseCookie jwtRefreshCookie = jwtUtils.generateRefreshJwtCookie(authentication);
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(authentication);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE,jwtCookie.toString())
                .header(HttpHeaders.SET_COOKIE,jwtRefreshCookie.toString())
                .build();
        //todo bad response if login info is bad
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        if (securityService.userExistsByEmail(registerDto.getEmail())) {
            return ResponseEntity.badRequest().body("Email already registered");
        }
        securityService.registerUser(registerDto);
        return ResponseEntity.ok(new ResponseDTO("User registered successfully"));
    }
    @PostMapping("signout")
    public ResponseEntity<?> logoutUser() {
        Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        System.out.println(principle.toString()); // todo remove

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



}
