package com.mstfcmrl.devicemanagement.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

@Configuration
public class FeignClientConfig {

    @Bean
    public RequestInterceptor requestTokenBearerInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                // Extract the Bearer token from the security context
                JwtAuthenticationToken authentication = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

                if (authentication != null && authentication.getToken() instanceof Jwt) {
                    String tokenValue = ((Jwt) authentication.getToken()).getTokenValue();
                    requestTemplate.header("Authorization", "Bearer " + tokenValue);
                }
            }
        };
    }
}
