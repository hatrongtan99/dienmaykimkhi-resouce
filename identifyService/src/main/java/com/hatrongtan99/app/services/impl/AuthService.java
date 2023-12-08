package com.hatrongtan99.app.services.impl;

import com.hatrongtan99.app.security.UserPrincipal;
import com.hatrongtan99.app.dto.UserDetailAuthResponseDto;
import com.hatrongtan99.app.dto.UserLoginRequestDto;
import com.hatrongtan99.app.dto.UserRegisterRequestDto;
import com.hatrongtan99.app.entity.RoleEntity;
import com.hatrongtan99.app.entity.UserEntity;
import com.hatrongtan99.app.exception.ConflictException;
import com.hatrongtan99.app.repository.RoleRepository;
import com.hatrongtan99.app.repository.UserRepository;
import com.hatrongtan99.app.services.IAuthService;
import com.hatrongtan99.app.utils.JwtUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public String userLogin(UserLoginRequestDto userLogin, HttpServletResponse response) throws Exception {
        Authentication authentication = new UsernamePasswordAuthenticationToken(userLogin.username(), userLogin.password());
        authentication = authenticationManager.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        String token = jwtUtils.getToken(principal);
        response.addCookie(new Cookie("Authorization", token));
        return "success";
    }

    @Override
    public UserDetailAuthResponseDto authenticated(UserPrincipal principal) throws Exception {
            String token = jwtUtils.getToken(principal);
            return new UserDetailAuthResponseDto(
                    principal.getId(),
                    principal.getFullName(),
                    principal.getUsername(),
                    principal.getEmail(),
                    principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList(),
                    token
            );
    }

    @Override
    public String register(UserRegisterRequestDto userInfo) {
        Optional<UserEntity> exists = userRepository.findByUsername(userInfo.username());
        if (exists.isPresent()) {
            throw new ConflictException("user " + exists.get().getUsername() + " already exists");
        }

        UserEntity newUser = UserEntity.builder()
                .fullName(userInfo.username())
                .username(userInfo.username())
                .email(userInfo.email())
                .password(passwordEncoder.encode(userInfo.password()))
                .build();
        RoleEntity role = roleRepository.findByName("USER").get();
        newUser.addRole(role);
        userRepository.save(newUser);
        return "success";
    }


}
