package com.hatrongtan99.app.security;

import com.hatrongtan99.app.entity.AuthorityEntity;
import com.hatrongtan99.app.entity.RoleEntity;
import com.hatrongtan99.app.entity.UserEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserPrincipal implements UserDetails {

    private Long id;
    private String fullName;
    private String username;
    private String email;
    private String password;
    private String imageUrl;
    private Set<RoleEntity> roles;
    private Set<AuthorityEntity> authorities;
    private boolean isActive;


    public static UserPrincipal create(UserEntity user) {
        return UserPrincipal.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .imageUrl(user.getImageUrl())
                .roles(user.getRoles())
                .authorities(user.getAuthorities())
                .isActive(user.isActive())
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> result = new ArrayList<>();
        for (RoleEntity role: roles) {
            result.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }
        for (AuthorityEntity authority : authorities) {
            result.add(new SimpleGrantedAuthority(authority.getName()));
        }
        return result;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return true;
    }
}
