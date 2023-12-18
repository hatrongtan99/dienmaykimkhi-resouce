package com.hatrongtan99.app.security.Oidc;

import com.hatrongtan99.app.entity.AuthorityEntity;
import com.hatrongtan99.app.entity.RoleEntity;
import com.hatrongtan99.app.entity.UserEntity;
import com.nimbusds.openid.connect.sdk.claims.UserInfo;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

import java.util.Collection;
import java.util.Set;
@Getter
@Setter
public class CustomOidcUser extends DefaultOidcUser implements UserDetails {

    private Long id;
    private String fullName;
    private String email;
    private Set<RoleEntity> roles;
    private Boolean isActive;
    private String imageUrl;

    public CustomOidcUser(Collection<? extends GrantedAuthority> authorities, OidcIdToken idToken) {
        super(authorities, idToken);
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isActive;
    }
}
