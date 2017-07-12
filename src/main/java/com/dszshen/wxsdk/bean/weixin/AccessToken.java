package com.dszshen.wxsdk.bean.weixin;

import com.dszshen.wxsdk.bean.JsonBean;

/**
 * Created by zhangbin on 2017/5/31 0031.
 */
public class AccessToken extends JsonBean {
    private String token;
    private Long expiresIn;

    public AccessToken() {
    }

    public AccessToken(String token,Long expiresIn) {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
