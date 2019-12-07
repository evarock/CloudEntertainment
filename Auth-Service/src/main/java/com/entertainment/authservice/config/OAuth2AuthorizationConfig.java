package com.entertainment.authservice.config;

import com.entertainment.authservice.service.AuthDatailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {
    private TokenStore tokenStore = new InMemoryTokenStore();
    private AuthenticationManager authenticationManager;
    private AuthDatailsService authDatailsService;

    @Autowired
    public OAuth2AuthorizationConfig(AuthenticationManager authenticationManager, AuthDatailsService authDatailsService) {
        this.authenticationManager = authenticationManager;
        this.authDatailsService = authDatailsService;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("gwt")
                .authorizedGrantTypes("refresh_token", "password")
                .scopes("read", "write")
                .secret("secret")
                .accessTokenValiditySeconds(20000)
                .refreshTokenValiditySeconds(20000)
                .and()
                .withClient("user-service")
                .secret("secret")
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .accessTokenValiditySeconds(20000)
                .refreshTokenValiditySeconds(20000)
                .scopes("server");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(authDatailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
