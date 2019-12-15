package com.entertainment.authservice.service;

import java.util.Optional;

import com.entertainment.authservice.model.AuthEntity;
import com.entertainment.authservice.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
	private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	private final AuthRepository authRepository;

	@Autowired
	public AuthServiceImpl(AuthRepository authRepository) {
		this.authRepository = authRepository;
	}

	@Override
	public AuthEntity create(AuthEntity user) {
		Optional<AuthEntity> existing = authRepository.findById(user.getUsername());
		existing.ifPresent(it -> {
			throw new IllegalArgumentException("User already exists: " + it.getUsername());
		});
		String hash = encoder.encode(user.getPassword());
		user.setPassword(hash);
		return authRepository.save(user);
	}

	@Override
	public AuthEntity getAuth(AuthEntity auth) {
		if (isInvalid(auth)) {
			throw new IllegalArgumentException("Auth user has empty username or password");
		}
		Optional<AuthEntity> existing = authRepository.findById(auth.getUsername());
		if (existing.isPresent()) {
			if (encoder.matches(auth.getPassword(), existing.get().getPassword())) {
				return auth;
			} else {
				throw new IllegalArgumentException("Auth service: password is invalid");
			}
		} else {
			throw new IllegalArgumentException("Auth service: user is not found");
		}
	}

	@Override
	public void delete(String username) {
		if (username == null) {
			throw new IllegalArgumentException("Username should not be empty");
		}
		authRepository.deleteById(username);
	}

	private boolean isInvalid(AuthEntity auth) {
		return auth == null || auth.getUsername() == null || auth.getUsername().isEmpty() ||
				auth.getPassword() == null || auth.getPassword().isEmpty();
	}
}