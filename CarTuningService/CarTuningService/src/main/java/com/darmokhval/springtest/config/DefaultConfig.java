package com.darmokhval.springtest.config;

import org.example.ApiClient;
import org.example.rest.CarApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

@Configuration
public class DefaultConfig {

    @Bean
    public ApiClient apiClient() {
        ApiClient apiClient = new ApiClient();
        apiClient.setBearerToken(() -> {
            JwtAuthenticationToken token = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            return token.getToken().getTokenValue();
        });
        apiClient.setDebugging(true);
        return apiClient;
    }

    @Bean
    public CarApi carApi() {
        return new CarApi(apiClient());
    }
}
