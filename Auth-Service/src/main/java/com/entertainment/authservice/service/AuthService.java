package com.entertainment.authservice.service;

import com.entertainment.authservice.model.AuthEntity;

public interface AuthService {
    AuthEntity create(AuthEntity user);
    AuthEntity getAuth(AuthEntity user);
}
