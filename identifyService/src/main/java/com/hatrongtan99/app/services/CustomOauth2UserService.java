package com.hatrongtan99.app.services;

import com.hatrongtan99.app.entity.RoleEntity;
import com.hatrongtan99.app.entity.UserEntity;
import com.hatrongtan99.app.repository.RoleRepository;
import com.hatrongtan99.app.repository.UserRepository;
import com.hatrongtan99.app.security.UserPrincipal;
import com.hatrongtan99.app.security.oauth2.Oauth2UserInfo;
import com.hatrongtan99.app.security.oauth2.Oauth2UserInfoFactory;
import com.hatrongtan99.enumeration.auth.TypeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class CustomOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        return processOauth2User(userRequest, oAuth2User);
    }

    private OAuth2User processOauth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        Oauth2UserInfo oauth2UserInfo = Oauth2UserInfoFactory.getOauth2UserInfo(userRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        // find user by provider id
        Optional<UserEntity> userEntityByProviderId = userRepository.findByProviderId(oauth2UserInfo.getId());
        UserEntity user = null;
        if (userEntityByProviderId.isPresent()) {
            user = userEntityByProviderId.get();
            if (TypeProvider.valueOf(user.getTypeProvider().toString()).getName().equals(userRequest.getClientRegistration().getRegistrationId())) {
                user = updateUser(user, oauth2UserInfo);
            } else {
                throw new AuthenticationException("Look like you singed up with " + user.getTypeProvider()) {
                };
            }
        } else {
            user = registerWithOauth2User(userRequest, oauth2UserInfo);
        }

        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }

    private UserEntity registerWithOauth2User(OAuth2UserRequest userRequest, Oauth2UserInfo oauth2UserInfo) {
        RoleEntity roleUser = roleRepository.findByName("USER").get();
        TypeProvider typeProvider = Arrays.stream(TypeProvider.values()).filter(t -> t.getName().equals(userRequest.getClientRegistration().getRegistrationId())).findFirst().get();
        UserEntity user = UserEntity.builder()
                .username(oauth2UserInfo.getFullName() + " : " + oauth2UserInfo.getId())
                .fullName(oauth2UserInfo.getFullName())
                .email(oauth2UserInfo.getEmail())
                .providerId(oauth2UserInfo.getId())
                .typeProvider(typeProvider)
                .build();
        user.addRole(roleUser);
        return userRepository.save(user);
    }


    private UserEntity updateUser(UserEntity user, Oauth2UserInfo userInfo) {
        // edit user info
        return userRepository.save(user);
    }
}
