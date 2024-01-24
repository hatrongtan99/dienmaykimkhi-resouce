package com.hatrongtan99.app.utils;

import com.hatrongtan99.app.exceptions.RequiredSignInException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Optional;

public class AuthenticationUtils {

    public static Long getAuthenticationUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) {
            throw new RequiredSignInException("Require sign in before process");
        }
        Jwt jwt = (Jwt) auth.getPrincipal();
        return Long.valueOf(jwt.getSubject());
    };

    public static Optional<Long> getAuditor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }
        Jwt jwt = (Jwt) auth.getPrincipal();
        return Optional.of(Long.valueOf(jwt.getSubject()));
    }
}
