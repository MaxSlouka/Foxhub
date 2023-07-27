package com.gfa.foxbook.foxbook.controllers;

import com.gfa.foxbook.foxbook.services.interfaces.LanguageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/public")
@CrossOrigin(origins = {"http://localhost:4200", "http://foxhub.gfapp.eu"}, maxAge = 3600, allowCredentials = "true")
public class LanguageController {

    private final LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping("/languages")
    public ResponseEntity<?> getAllLanguages() {
        return ResponseEntity.ok(languageService.getAllLanguages());
    }
}
