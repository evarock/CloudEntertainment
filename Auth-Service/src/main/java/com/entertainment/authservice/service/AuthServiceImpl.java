package com.entertainment.authservice.service;

import java.util.Optional;

import com.entertainment.authservice.model.AuthEntity;
import com.entertainment.authservice.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
	public void create(AuthEntity user) {
		Optional<AuthEntity> existing = authRepository.findById(user.getUsername());
		existing.ifPresent(it -> {
			throw new IllegalArgumentException("User already exists: " + it.getUsername());
		});
		String hash = encoder.encode(user.getPassword());
		user.setPassword(hash);
		authRepository.save(user);
	}
}