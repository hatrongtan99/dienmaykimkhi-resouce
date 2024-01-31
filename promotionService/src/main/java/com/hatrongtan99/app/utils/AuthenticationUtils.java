package com.hatrongtan99.app.utils;

import com.hatrongtan99.app.exception.RequiredSignInException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Optional;

public class AuthenticationUtils {

    private AuthenticationUtils() {

    }
    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new RequiredSignInException("require sign in before process");
        }
        return null;
    }

    public static Optional<Long> getAuditor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken || auth == null) {
            return Optional.empty();
        }
        Jwt jwt = (Jwt) auth.getPrincipal();
        return Optional.of(Long.valueOf(jwt.getSubject()));
    }
}
