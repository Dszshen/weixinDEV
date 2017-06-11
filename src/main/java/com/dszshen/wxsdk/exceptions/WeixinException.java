package com.dszshen.wxsdk.exceptions;

/**
 * Created by Administrator on 2017/5/29 0029.
 */
public class WeixinException extends RuntimeException{

    private static final long serialVersionUID = -63967660388730776L;

    public WeixinException() {
    }

    public WeixinException(String message) {
        super(message);
    }

    public WeixinException(String message, Throwable cause) {
        super(message, cause);
    }

    public WeixinException(Throwable cause) {
        super(cause);
    }

    public WeixinException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
