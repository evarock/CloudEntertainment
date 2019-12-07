package com.entertainment.authservice.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Component
@Table(name = "users", catalog = "ent_auth_users")
public class AuthEntity implements UserDetails {
    private String username;
    private String password;

    protected AuthEntity() { }

    @Id
    @Column(name = "username", length = 50, nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull
    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Transient
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Transient
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Transient
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Transient
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Transient
    @Override
    public boolean isEnabled() {
        return false;
    }
}
