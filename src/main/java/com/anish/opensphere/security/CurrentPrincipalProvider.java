package com.anish.opensphere.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CurrentPrincipalProvider {

    public Optional<Long> getCurrentPrincipalId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null || !auth.isAuthenticated() ||
                !(auth.getPrincipal() instanceof  CustomUserDetails principal)) {
            return Optional.empty();
        }
        return Optional.ofNullable(principal.getPrincipalId());
    }
}