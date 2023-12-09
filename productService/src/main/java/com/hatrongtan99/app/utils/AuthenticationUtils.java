package com.hatrongtan99.app.utils;

import com.hatrongtan99.app.security.UserPrincipal;
import com.hatrongtan99.exception.RequiredSignInException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuthenticationUtils {

    private AuthenticationUtils() {

    }
    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new RequiredSignInException("require sign in before process");
        }
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return userPrincipal.getId();
    }

    public static Optional<Long> getCurrentAuditing() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return Optional.of(userPrincipal.getId());
    }
}
