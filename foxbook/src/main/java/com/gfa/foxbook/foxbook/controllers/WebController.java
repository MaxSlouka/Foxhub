package com.gfa.foxbook.foxbook.controllers;

import com.gfa.foxbook.foxbook.models.User;
import com.gfa.foxbook.foxbook.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class WebController {
    private UserService userService;

    @GetMapping("")
    public String index() {
        return "index.html";
    }

    @GetMapping("login")
    public String login() {
        return "login.html";
    }

    @GetMapping("registration")
    public String registration() {
        return "registration.html";
    }

    @GetMapping("main")
    public String main() {
        return "main.html";
    }

    @GetMapping("profile")
    public String profile() {
        return "profile.html";
    }

    @GetMapping("people")
    public String getAllUsers() {
        return "people.html";
    }

    @GetMapping("person/{nickname}")
    public String redirect(@PathVariable String nickname) {
        Optional<User> maybeUser = userService.findByNickname(nickname);

        if (maybeUser.isPresent()) {
            return "redirect:" + userService.accessProfileUrl(maybeUser.get());
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
