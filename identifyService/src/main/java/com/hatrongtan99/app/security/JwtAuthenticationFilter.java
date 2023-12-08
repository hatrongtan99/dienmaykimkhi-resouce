package com.hatrongtan99.app.security;

import com.hatrongtan99.app.entity.UserEntity;
import com.hatrongtan99.app.repository.UserRepository;
import com.hatrongtan99.app.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = extractTokenFromRequest(request);
        if (token != null) {
            try {
                Long userId = jwtUtils.getUserId(token);
                UserEntity user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("user not found"));
                if (jwtUtils.verifyToken(token, user.getUsername()))  {
                    UserPrincipal userDetail = UserPrincipal.builder()
                            .id(user.getId())
                            .fullName(user.getFullName())
                            .username(user.getUsername())
                            .email(user.getEmail())
                            .roles(user.getRoles())
                            .authorities(user.getAuthorities())
                            .build();
                    Authentication authentication = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        filterChain.doFilter(request, response);
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String token = null;
        if (cookies != null) {
            Optional<Cookie> cookieAuth = Arrays.stream(cookies).filter(cookie -> cookie.getName().equals("Authorization")).findFirst();
            if (cookieAuth.isPresent()) {
                token = cookieAuth.get().getValue();
            }
        }

        if (!StringUtils.hasText(token)) {
            token = request.getHeader("Authorization");
            if (StringUtils.hasText(token) && token.startsWith("Bearer")) {
                return token.substring(7);
            }
        }
        return token;
    }
}
