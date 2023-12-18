package com.hatrongtan99.app.security.Oidc;

import com.hatrongtan99.app.entity.UserEntity;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public interface IOidcUserMapper {
    OidcUser map(OidcUser oidcUser);

    OidcUser map(OidcUser oidcUser,  UserEntity user);
}
