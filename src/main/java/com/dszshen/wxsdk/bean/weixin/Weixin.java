package com.dszshen.wxsdk.bean.weixin;

import com.alibaba.fastjson.JSONObject;
import com.belerweb.social.http.HttpException;
import com.dszshen.wxsdk.common.util.http.Http;
import com.dszshen.wxsdk.exceptions.WeixinException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangbin on 2017/5/27 0027.
 */
public final class Weixin {

    private String appId;
    private String secret;
    private String token;
    private String redirectUri;
    private AccessToken accessToken = new AccessToken();
    private Date accessTokenTime;
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
        if(this.accessToken == null || this.accessTokenTime == null ||
            ((new Date()).getTime() - this.accessTokenTime.getTime()) / 1000L > this.accessToken.getExpiresIn().longValue()) {
            List<NameValuePair> params = new ArrayList();
            NameValuePair nameValuePair0 = new BasicNameValuePair("appid", this.appId);
            NameValuePair nameValuePair1 = new BasicNameValuePair("secret", this.secret);
            NameValuePair nameValuePair2 = new BasicNameValuePair("grant_type", "client_credential");
            params.add(nameValuePair0);
            params.add(nameValuePair1);
            params.add(nameValuePair2);
            try{
                String json = Http.get("https://api.weixin.qq.com/cgi-bin/token?" + StringUtils.join(params, "&"), params);
                JSONObject jsonObject = JSONObject.parseObject(json);
                System.out.println("getAccessToken===============>"+jsonObject);
                //AccessToken accessToken = new AccessToken(jsonObject.getString("access_token"),jsonObject.getLong("expires_in"));
                this.accessToken.setToken(jsonObject.getString("access_token"));
                this.accessToken.setExpiresIn(jsonObject.getLong("expires_in"));
            }catch (HttpException e){
                throw new WeixinException(e);
            }
        }

        return this.accessToken;
    }

    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    public Date getAccessTokenTime() {
        return accessTokenTime;
    }

    public void setAccessTokenTime(Date accessTokenTime) {
        this.accessTokenTime = accessTokenTime;
    }
}
