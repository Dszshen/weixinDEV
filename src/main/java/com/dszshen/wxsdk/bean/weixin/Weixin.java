package com.dszshen.wxsdk.bean.weixin;

/**
 * Created by zhangbin on 2017/5/27 0027.
 */
public final class Weixin {

    private String appId;
    private String secret;
    private String token;
    private String redirectUri;
    private AccessToken accessToken;
    private Menu menu;

    public Weixin() {}

    public Weixin(String token){
        this.token = token;
    }

    public Weixin(String appId, String secret){
        this.appId = appId;
        this.secret = secret;
    }

    public Weixin(String appId, String secret, String token){
        this(appId, secret);
        this.token = token;
    }

    public Weixin(String appid, String secret, String redirectUri, String token){
        this(appid, secret, token);
        this.redirectUri = redirectUri;
    }

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

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }
}
