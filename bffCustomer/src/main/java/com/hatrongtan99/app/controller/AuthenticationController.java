package com.hatrongtan99.app.controller;

import com.hatrongtan99.app.dto.UserNameResponseDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {

    @GetMapping("/authenticate")
    public Mono<UserNameResponseDto> getAuthentication(
            @AuthenticationPrincipal OAuth2AuthenticationToken principal
    ) {
        OidcUser oidcUser = (OidcUser) principal.getPrincipal();
        String username = oidcUser.getClaim("username");
        String fullName = oidcUser.getClaim("fullName");
        String email = oidcUser.getClaim("email");
        return Mono.just(new UserNameResponseDto( username, fullName, email));
    }
}
