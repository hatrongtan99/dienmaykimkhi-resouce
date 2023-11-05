package com.hatrongtan99.app.service;

import com.hatrongtan99.app.dto.UserLoginRequestDto;
import com.hatrongtan99.app.dto.UserLoginResponseDto;
import com.hatrongtan99.app.dto.UserRegisterRequestDto;
import com.hatrongtan99.app.dto.UserRegisterResponseDto;
import com.hatrongtan99.app.entity.Role;
import com.hatrongtan99.app.entity.UserEntity;
import com.hatrongtan99.app.repository.RoleRepository;
import com.hatrongtan99.app.repository.UserRepository;
import com.hatrongtan99.app.utils.JwtUtils;
import com.hatrongtan99.app.exception.ConflictException;
import com.hatrongtan99.app.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService implements IUserService {
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
    public UserLoginResponseDto userLogin(UserLoginRequestDto userLoginRequestDto) {

        Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginRequestDto.username(),
                userLoginRequestDto.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserEntity user = this.getUserEntityFromAuthentication(authentication);
        String token = this.createToken(user);

        return new UserLoginResponseDto(token);


    }

    @Override
    public UserRegisterResponseDto userRegister(UserRegisterRequestDto userRegister) {
        boolean userExist = this.userRepository.existsByUsername(userRegister.username());
        if (userExist) {
            throw new ConflictException("User with username " + userRegister.username() + " already exist");
        }
        UserEntity newUser = UserEntity.builder()
                .username(userRegister.username())
                .email(userRegister.email())
                .password(this.passwordEncoder.encode(userRegister.password()))
                .build();
        Role roleUser = this.roleRepository.findByName("USER");
        newUser.addRole(roleUser);
        newUser = this.userRepository.save(newUser);
        System.out.println(newUser);
        return new UserRegisterResponseDto();
    }

    private String createToken(UserEntity user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", user.getRoles());
        claims.put("username", user.getUsername());
        claims.put("email", user.getEmail());
        return this.jwtUtils.generateToken(user.getId(), claims);
    }

    private UserEntity getUserEntityFromAuthentication(Authentication authentication) {
        return this.userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new NotFoundException("User with username " + authentication.getName() + " not found"));
    }
}
