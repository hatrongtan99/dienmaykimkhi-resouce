package com.hatrongtan99.app.security;

import com.hatrongtan99.app.entity.RoleEntity;
import com.hatrongtan99.app.entity.UserEntity;
import com.hatrongtan99.app.repository.RoleRepository;
import com.hatrongtan99.app.repository.UserRepository;
import com.hatrongtan99.app.security.oauth2.Oauth2UserInfo;
import com.hatrongtan99.app.security.oauth2.Oauth2UserInfoFactory;
import com.hatrongtan99.enumeration.auth.TypeProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        return processUser(userRequest, oAuth2User);
    }

    private OAuth2User processUser(OAuth2UserRequest userRequest, OAuth2User oauth2User) {
        String clientRegisterId = userRequest.getClientRegistration().getRegistrationId();
        Oauth2UserInfo oauth2UserInfo = Oauth2UserInfoFactory.getOauth2UserInfo(clientRegisterId, oauth2User.getAttributes());

        String providerId = oauth2UserInfo.getProviderId();

        Optional<UserEntity> user = this.userRepository.findByProviderId(providerId);
        if (user.isPresent()) {
            return UserPrincipal.create(user.get(), oauth2User.getAttributes());
        }
        RoleEntity role = roleRepository.findByName("USER").get();
        TypeProvider typeProvider = TypeProvider.getByName(clientRegisterId);
        assert typeProvider != null;
        UserEntity newUser= UserEntity.builder()
                .providerId(providerId)
                .username(null)
                .fullName(oauth2UserInfo.getFullName())
                .email(oauth2UserInfo.getEmail())
                .typeProvider(typeProvider)
                .roles(Set.of(role))
                .build();

        this.userRepository.save(newUser);
        return UserPrincipal.create(newUser, oauth2User.getAttributes());
    };
}
