package com.gfa.foxbook.foxbook.controllers;
import com.gfa.foxbook.foxbook.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ApiController {

    private final UserService userService;


    public ApiController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/people/{id}")
    public ResponseEntity<?> personDetails(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }
}
