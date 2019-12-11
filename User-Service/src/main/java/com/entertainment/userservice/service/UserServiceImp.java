package com.entertainment.userservice.service;

import com.entertainment.userservice.model.UserEntity;
import com.entertainment.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity getUser(String username) {
        Optional<UserEntity> existing = userRepository.findById(username);
        if (existing.isPresent()) {
            return existing.get();
        } else {
            throw new IllegalArgumentException("User is not found");
        }
    }

    @Override
    public UserEntity create(UserEntity user) {
        if (isInvalid(user)) {
            throw new IllegalArgumentException("Invalid user's parameters");
        }
        Optional<UserEntity> existing = userRepository.findById(user.getUsername());
        existing.ifPresent(it -> {
            throw new IllegalArgumentException("User already exists: " + it.getUsername());
        });
        return userRepository.save(user);
    }

    @Override
    public UserEntity update(UserEntity user) {
        if (isInvalid(user)) {
            throw new IllegalArgumentException("Invalid user's parameters");
        }
        Optional<UserEntity> existing = userRepository.findById(user.getUsername());
        if (existing.isPresent()) {
            return userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User is not found");
        }
    }

    @Override
    public void delete(String username) {
        if (username == null) {
            throw new IllegalArgumentException("Username should not be empty");
        }
        userRepository.deleteById(username);
    }

    private boolean isInvalid(UserEntity user) {
        return user == null || user.getOrganization() == null || user.getInitDate() == null;
    }
}
