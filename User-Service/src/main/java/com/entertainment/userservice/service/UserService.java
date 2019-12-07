package com.entertainment.userservice.service;

import com.entertainment.userservice.model.UserEntity;

public interface UserService {
    UserEntity getUser(String username);
    UserEntity create(UserEntity user);
    UserEntity update(UserEntity user);
    void delete(String username);
}
