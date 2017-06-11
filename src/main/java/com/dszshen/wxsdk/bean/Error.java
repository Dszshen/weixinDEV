package com.dszshen.wxsdk.bean;

import com.alibaba.fastjson.JSONObject;
import com.belerweb.social.bean.Result;

/**
 * Created by zhangbin on 2017/5/27 0027.
 */
public final class Error extends JsonBean{
    private String request;
    private String errorCode;
    private String error;

    public Error() {
    }

    public Error(String code, String message) {
        this.errorCode = code;
        this.error = message;
    }

    private Error(JSONObject jsonObject) {
        super(jsonObject);
    }

    public String getRequest() {
        return this.request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getErrorBeanCode() {
        return this.errorCode;
    }

    public void setErrorBeanCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorBean() {
        return this.error;
    }

    public void setErrorBean(String error) {
        this.error = error;
    }

    public String toString() {
        return this.errorCode + ":" + this.error + "(" + this.request + ")";
    }

    /*public static Error parse(JSONObject jsonObject) {
        String errorCode = jsonObject.optString("error_code", (String)null);
        String error;
        Error er;
        if(errorCode != null) {
            error = jsonObject.optString("request", (String)null);
            error = jsonObject.optString("error", (String)null);
            er = new Error(jsonObject);
            er.setRequest(error);
            er.setErrorBeanCode(errorCode);
            er.setErrorBean(error);
            return er;
        } else {
            errorCode = jsonObject.optString("error", (String)null);
            if(errorCode != null) {
                error = jsonObject.optString("error_description", (String)null);
                Error er1 = new Error(jsonObject);
                er1.setErrorBeanCode(errorCode);
                er1.setErrorBean(error);
                return er1;
            } else {
                Integer ret = Result.parseInteger(jsonObject.opt("ret"));
                if(ret != null && ret.intValue() != 0) {
                    error = jsonObject.optString("msg", (String)null);
                    er = new Error(jsonObject);
                    er.setErrorBeanCode(ret.toString());
                    er.setErrorBean(error);
                    return er;
                } else {
                    ret = Result.parseInteger(jsonObject.opt("errcode"));
                    if(ret != null && ret.intValue() != 0) {
                        error = jsonObject.optString("errmsg", (String)null);
                        er = new Error(jsonObject);
                        er.setErrorBeanCode(ret.toString());
                        er.setErrorBean(error);
                        return er;
                    } else {
                        return null;
                    }
                }
            }
        }
    }*/
}
