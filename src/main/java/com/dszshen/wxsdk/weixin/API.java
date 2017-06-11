package com.dszshen.wxsdk.weixin;

import com.dszshen.wxsdk.weixin.bean.Weixin;

/**
 * Created by zhangbin on 2017/5/29 0029.
 */
public abstract class API {
    protected Weixin weixin;

    public Weixin getWeixin() {
        return weixin;
    }

    public void setWeixin(Weixin weixin) {
        this.weixin = weixin;
    }
}
