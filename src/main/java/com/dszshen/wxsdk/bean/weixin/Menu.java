package com.dszshen.wxsdk.bean.weixin;

import com.alibaba.fastjson.JSONObject;
import com.dszshen.wxsdk.bean.JsonBean;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * Created by zhangbin on 2017/5/31 0031.
 */
@Entity
@Table(name = "weixin_menu")
public class Menu extends JsonBean {

  @Id
  @GenericGenerator(name = "generator", strategy = "uuid")
  @GeneratedValue(generator = "generator")
  @Column(name="id")
  private String id;
  private String pid;
  private String type;
  private String key;
  private String url;
  private String name;

  public Menu() {
  }

  public Menu(JSONObject jsonObject) {
    super(jsonObject);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPid() {
    return pid;
  }

  public void setPid(String pid) {
    this.pid = pid;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
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
}
