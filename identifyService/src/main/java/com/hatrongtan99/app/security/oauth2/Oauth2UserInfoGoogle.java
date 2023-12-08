package com.hatrongtan99.app.security.oauth2;

import java.util.Map;

public class Oauth2UserInfoGoogle extends Oauth2UserInfo{

    public Oauth2UserInfoGoogle(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return (String) this.attributes.get("sub");
    }

    @Override
    public String getFullName() {
        return (String) this.attributes.get("name");
    }

    @Override
    public String getEmail() {
        return (String) this.attributes.get("email");
    }

    @Override
    public String getUrlImage() {
        return (String) this.attributes.get("picture");
    }
}
