//package com.entertainment.authservice.service;
//
//import com.entertainment.authservice.repository.AuthRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthDatailsService implements UserDetailsService {
//    private AuthRepository authRepository;
//
//    @Autowired
//    public AuthDatailsService(AuthRepository authRepository) {
//        this.authRepository = authRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return authRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException(username));
//    }
//}
