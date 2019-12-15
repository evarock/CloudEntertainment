package com.entertainment.userservice.controller;

import com.entertainment.userservice.model.Gender;
import com.entertainment.userservice.model.UserEntity;
import com.entertainment.userservice.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final UserServiceImp userService;

    @Autowired
    public UserController(UserServiceImp userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity getUser(@PathVariable String username) {
        UserEntity user;
        try {
            user = userService.getUser(username);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping(value = "/")
    public ResponseEntity addUser(@RequestBody Map<String, Object> values) {
        if (values == null || values.size() != 1) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("UserService has wrong input parameters");
        }
        UserEntity user;
        try {
            String username = (String) values.get("username");
            if (username == null || username.isEmpty()) {
                throw new Exception("UserService: username is empty");
            }
            user = userService.create(new UserEntity(username, null, null, null, Gender.UNKNOWN,
                    new Date(),false));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateUser(@RequestBody UserEntity user) {
        try {
            userService.update(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(value = "/{username}")
    public ResponseEntity deleteUser(@PathVariable String username) {
        try {
            userService.delete(username);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}
