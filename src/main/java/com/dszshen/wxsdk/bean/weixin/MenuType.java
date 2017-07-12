package com.dszshen.wxsdk.bean.weixin;

/**
 * Created by zhangbin on 2017/5/31 0031.
 */
public enum MenuType {
    CLICK("click"),
    VIEW("view");

    private String type;

    private MenuType(String type) {
        this.type = type;
    }

    public String value() {
        return this.type;
    }

    public String toString() {
        return this.type;
    }

    public static MenuType parse(Object val) {
        return CLICK.type.equals(val)?CLICK:(VIEW.type.equals(val)?VIEW:null);
    }
}
