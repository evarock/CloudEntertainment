package com.entertainment.authservice.controller;

import com.entertainment.authservice.model.AuthEntity;
import com.entertainment.authservice.service.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {
    private final RestTemplate restTemplate;
    private final AuthServiceImpl authService;

    @Autowired
    public AuthController(AuthServiceImpl authService, RestTemplate restTemplate) {
        this.authService = authService;
        this.restTemplate = restTemplate;
    }

    @PostMapping(value = "/get", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAuth(@RequestBody AuthEntity authEntity) {
//        String user;
//        try {
//            AuthEntity auth = authService.getAuth(authEntity);
//            user = restTemplate.getForObject("http://localhost:8762/users/" + auth.getUsername(), String.class);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
//        }
//        return ResponseEntity.ok(user);
        AuthEntity auth;
        try {
            auth = authService.getAuth(authEntity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addAuth(@RequestBody AuthEntity authEntity) {
        try {
            authService.create(authEntity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        return ResponseEntity.ok(authEntity);
    }
}
