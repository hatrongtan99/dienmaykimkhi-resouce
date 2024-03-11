package com.hatrongtan99.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.HttpStatusServerEntryPoint;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EnableWebFluxSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        RedirectServerAuthenticationSuccessHandler redirectSuccessHandler = new RedirectServerAuthenticationSuccessHandler();
        redirectSuccessHandler.setLocation(URI.create("http://localhost:8080/auth/success"));

        http
                .authorizeExchange(
                        exchanges -> exchanges
                                .pathMatchers("/api/v1/authenticate").authenticated()
                                .anyExchange().permitAll()
                )
                .exceptionHandling(ex -> ex.authenticationEntryPoint(new HttpStatusServerEntryPoint(HttpStatus.UNAUTHORIZED)))
                .oauth2Login(oauth2 -> oauth2.authenticationSuccessHandler(redirectSuccessHandler))
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .csrf(ServerHttpSecurity.CsrfSpec::disable);
        return http.build();
    }

    @Bean
    public GrantedAuthoritiesMapper userAuthoritiesMapper() {
        return (authorities -> {
            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();
            authorities.forEach(authority -> {
                if (authority instanceof OidcUserAuthority oidcUserAuthority) {
                    List<String> roles = oidcUserAuthority.getIdToken().getClaimAsStringList("authorities");
                    roles.forEach(role -> mappedAuthorities.add(new SimpleGrantedAuthority(role)));
                } else
                    if (authority instanceof OAuth2UserAuthority oAuth2UserAuthority) {
                    List<String> roles = (List<String>) oAuth2UserAuthority.getAttributes().get("authorities");
                    roles.stream().map(role -> mappedAuthorities.add(new SimpleGrantedAuthority(role)));
                }
            });
            return mappedAuthorities;
        });
    }
}
