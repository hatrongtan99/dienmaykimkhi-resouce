package com.hatrongtan99.app.services;

import com.hatrongtan99.app.entity.UserEntity;
import com.hatrongtan99.app.repository.UserRepository;
import com.hatrongtan99.app.security.Oidc.IOidcUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomOidcUserService extends OidcUserService {
    @Autowired
    private IOidcUserMapper oidcUserMapper;

    @Autowired
    private UserRepository userRepository;
    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        String providerId = userRequest.getIdToken().getSubject();
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        Optional<UserEntity> exitsUser = this.userRepository.findByProviderId(providerId);
        UserEntity user = null;
        if (exitsUser.isPresent()) {
            user = exitsUser.get();
            return this.oidcUserMapper.map(oidcUser, user);
        }
        return this.oidcUserMapper.map(oidcUser);
    }

}
