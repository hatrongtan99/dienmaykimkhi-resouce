package com.hatrongtan99.app.controller;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @GetMapping("/authenticate")
    public String getAuthentication(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client) {
        return client.getAccessToken().getTokenValue();
    }

}
