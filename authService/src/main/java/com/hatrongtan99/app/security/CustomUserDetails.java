package com.hatrongtan99.app.security;

import com.hatrongtan99.app.entity.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class CustomUserDetails implements UserDetails {

    private Long id;
    private String userName;
    private String email;
    private String password;
    private Set<Role> roles;

    public CustomUserDetails(Long id, String userName, String email, String password, Set<Role> roles) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public CustomUserDetails(String userName, Set<Role> roles) {
        this.userName = userName;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName())).toList();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
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
