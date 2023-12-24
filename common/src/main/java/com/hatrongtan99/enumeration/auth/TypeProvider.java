package com.hatrongtan99.enumeration.auth;

public enum TypeProvider {
    GOOGLE("google"), FACEBOOK("facebook"), LOCAL("local"), GITHUB("github");

    private final String name;

    TypeProvider(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
