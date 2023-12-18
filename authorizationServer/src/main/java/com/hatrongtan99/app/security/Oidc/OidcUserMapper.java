package com.hatrongtan99.app.security.Oidc;

import com.hatrongtan99.app.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OidcUserMapper implements IOidcUserMapper {
    @Override
    public OidcUser map(OidcUser oidcUser, UserEntity user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().stream().map(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));
        user.getAuthorities().stream().map(authority -> authorities.add(new SimpleGrantedAuthority(authority.getName())));
        CustomOidcUser customOidcUser = new CustomOidcUser(authorities, oidcUser.getIdToken());
        customOidcUser.setId(user.getId());
        customOidcUser.setIsActive(user.isActive());
        customOidcUser.setEmail(user.getEmail());
        customOidcUser.setFullName(user.getFullName());
        return customOidcUser;
    }

    @Override
    public OidcUser map(OidcUser oidcUser) {
        CustomOidcUser customOidcUser = new CustomOidcUser(null, oidcUser.getIdToken());
        customOidcUser.setEmail(oidcUser.getEmail());
        customOidcUser.setFullName(oidcUser.getFullName());
        customOidcUser.setIsActive(true);
        return customOidcUser;
    }
}
