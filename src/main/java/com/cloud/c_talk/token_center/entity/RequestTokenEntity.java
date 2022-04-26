package com.cloud.c_talk.token_center.entity;

import java.io.Serializable;

public class RequestTokenEntity implements Serializable {

    private String username;
    private String token;

    public String getToken() {
        return token;
    }

    public RequestTokenEntity setToken(String token) {
        this.token = token;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public RequestTokenEntity setUsername(String username) {
        this.username = username;
        return this;
    }

}
