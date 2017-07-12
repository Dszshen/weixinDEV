package com.dszshen.wxsdk.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by zhangbin on 2017/7/12 0012.
 */
@Entity
@Table(name = "weixin_config")
public class ConfigBean {

  @Id
  @GenericGenerator(name = "generator", strategy = "assigned")
  @GeneratedValue(generator = "generator")
  @Column(name="name")
  private String name;
  private String value;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
