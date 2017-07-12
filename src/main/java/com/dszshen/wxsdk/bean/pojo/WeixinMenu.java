package com.dszshen.wxsdk.bean.pojo;

import com.alibaba.fastjson.JSONObject;
import com.dszshen.wxsdk.bean.JsonBean;
import com.dszshen.wxsdk.bean.weixin.MenuType;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by zhangbin on 2017/5/31 0031.
 */
public class WeixinMenu{
    private MenuType type;
    private String key;
    private String url;
    private String name;
    private List<WeixinMenu> subMenus;

    public WeixinMenu() {
    }

    public MenuType getType() {
        return type;
    }

    public void setType(MenuType type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WeixinMenu> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<WeixinMenu> subMenus) {
        this.subMenus = subMenus;
    }
}
