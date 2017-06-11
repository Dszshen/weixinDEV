package com.dszshen.wxsdk.weixin.bean;

import com.alibaba.fastjson.JSONObject;
import com.belerweb.social.bean.Result;
import com.dszshen.wxsdk.bean.JsonBean;

import java.util.List;

/**
 * Created by zhangbin on 2017/5/31 0031.
 */
public class Menu extends JsonBean {
    private MenuType type;
    private String key;
    private String url;
    private String name;
    private List<Menu> subMenus;

    public Menu() {
    }

    public Menu(JSONObject jsonObject){
        super(jsonObject);
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

    public List<Menu> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<Menu> subMenus) {
        this.subMenus = subMenus;
    }

    public static Menu parse(JSONObject jsonObject) {
        if(jsonObject == null) {
            return null;
        } else {
            Menu obj = new Menu(jsonObject);
            obj.name = Result.toString(jsonObject.get("name"));
            obj.key = Result.toString(jsonObject.get("key"));
            obj.url = Result.toString(jsonObject.get("url"));
            obj.type = MenuType.parse(jsonObject.get("type"));
            //obj.subMenus = Result.parse(jsonObject.getJSONArray("sub_button"), Menu.class);
            return obj;
        }
    }
}
