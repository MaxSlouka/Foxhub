package com.gfa.foxbook.foxbook.controllers;
import com.gfa.foxbook.foxbook.models.User;
import com.gfa.foxbook.foxbook.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final UserService userService;


    public ApiController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/people/{id}")
    public ResponseEntity<?> personDetails(@RequestParam Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }
}
