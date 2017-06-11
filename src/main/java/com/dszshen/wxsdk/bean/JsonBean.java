package com.dszshen.wxsdk.bean;


import com.alibaba.fastjson.JSONObject;

/**
 * Created by zhangbin on 2017/5/27 0027.
 */
public abstract class JsonBean {
    private JSONObject jsonObject;

    protected JsonBean() {
    }

    protected JsonBean(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public JSONObject getJsonObject() {
        return this.jsonObject;
    }
}
