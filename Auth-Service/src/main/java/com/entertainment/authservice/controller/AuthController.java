package com.entertainment.authservice.controller;

import com.entertainment.authservice.model.AuthEntity;
import com.entertainment.authservice.service.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {
    private final RestTemplate restTemplate;
    private final AuthServiceImpl authService;
    private final String userURL = "http://localhost:8762/users/";

    @Autowired
    public AuthController(AuthServiceImpl authService, RestTemplate restTemplate) {
        this.authService = authService;
        this.restTemplate = restTemplate;
    }

    @PostMapping(value = "/get", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAuth(@RequestBody AuthEntity authEntity) {
        Object user;
        try {
            AuthEntity auth = authService.getAuth(authEntity);
            user = restTemplate.getForObject(userURL + auth.getUsername(), Object.class);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addAuth(@RequestBody AuthEntity authEntity) {
        Object user;
        boolean isCreated = false;
        try {
            AuthEntity auth = authService.create(authEntity);
            if (auth == null) {
                throw new Exception("Auth service: failed to create user");
            }
            isCreated = true;
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            Map<String, Object> map = new HashMap<>();
            map.put("username", auth.getUsername());
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
            ResponseEntity<Object> response = restTemplate.postForEntity(userURL, entity, Object.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                user = response.getBody();
            } else {
                throw new Exception("AuthService received wrong response:" + response.getStatusCode());
            }
        } catch (Exception e) {
            if (isCreated) {
                try {
                    authService.delete(authEntity.getUsername());
                } catch (Exception ignored) {}
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(value = "/{username}")
    public ResponseEntity deleteAuth(@PathVariable String username) {
        try {
            authService.delete(username);
            restTemplate.delete(userURL + username);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}
