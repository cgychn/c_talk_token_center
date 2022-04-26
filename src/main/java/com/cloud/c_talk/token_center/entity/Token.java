package com.cloud.c_talk.token_center.entity;

import com.alibaba.fastjson.JSONObject;
import com.cloud.c_talk.token_center.utils.MD5Util;

import java.io.Serializable;
import java.util.UUID;

public class Token implements Serializable {
    private String username;
    private String hash;
    private String uuid;
    private long expiryTime = 1000 * 60 * 60 * 2; // 2h
    private long lastLoggedTime = System.currentTimeMillis();

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Token(String username) {
        uuid = UUID.randomUUID().toString();
        this.username = username;
        this.hash = MD5Util.encrypt(username + " " + uuid);
    }

    public Token() {

    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(long expiryTime) {
        this.expiryTime = expiryTime;
    }

    public long getLastLoggedTime() {
        return lastLoggedTime;
    }

    public void setLastLoggedTime(long lastLoggedTime) {
        this.lastLoggedTime = lastLoggedTime;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
