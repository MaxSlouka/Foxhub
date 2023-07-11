package com.gfa.foxbook.foxbook.controllers;

import com.gfa.foxbook.foxbook.security.JWTGenerator;
import com.gfa.foxbook.foxbook.services.SecurityService;
import com.gfa.foxbook.foxbook.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1/public")
public class PublicController {
    private final UserService userService;

    @GetMapping("/people")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok().body(userService.findAll());
    }
}
