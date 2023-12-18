package com.hatrongtan99.app.security.oauth2;

import com.hatrongtan99.enumeration.auth.TypeProvider;
import org.springframework.security.core.AuthenticationException;

import java.util.Map;

public class Oauth2UserInfoFactory {
    public static Oauth2UserInfo getOauth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if (registrationId.equals(TypeProvider.GOOGLE.getName())) {
            return new Oauth2UserInfoGoogle(attributes);
        } else if (registrationId.equals(TypeProvider.GITHUB.getName())) {
            return new Oauth2UserInfoGithub(attributes);
        } else {
            throw new AuthenticationException("Registration Oauth2 not found") {
            };
        }
    }
}
