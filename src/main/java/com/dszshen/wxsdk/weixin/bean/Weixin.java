package com.dszshen.wxsdk.weixin.bean;

import com.dszshen.wxsdk.weixin.SDK;

/**
 * Created by zhangbin on 2017/5/27 0027.
 */
public final class Weixin extends SDK {
    private String appId;
    private String secret;
    private String token;
    private AccessToken accessToken;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }
}
