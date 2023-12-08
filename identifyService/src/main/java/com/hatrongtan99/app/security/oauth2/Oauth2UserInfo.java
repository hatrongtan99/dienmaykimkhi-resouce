package com.hatrongtan99.app.security.oauth2;

import java.util.Map;

public abstract class Oauth2UserInfo {
    protected Map<String, Object> attributes;
    public Oauth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public abstract String getId();

    public abstract String getFullName();

    public abstract String getEmail();

    public abstract String getUrlImage();
}
