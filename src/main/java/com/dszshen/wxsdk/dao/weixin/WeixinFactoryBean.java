package com.dszshen.wxsdk.dao.weixin;

import com.alibaba.fastjson.JSONObject;
import com.dszshen.wxsdk.bean.ConfigBean;
import com.dszshen.wxsdk.bean.weixin.Weixin;
import com.dszshen.wxsdk.service.weixin.ConfigChanged;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zhangbin on 2017/7/12 0012.
 */
@Component
public class WeixinFactoryBean implements FactoryBean<Weixin>,ConfigChanged {

  private Weixin weixin = new Weixin(StringUtils.EMPTY);

  public void configChanged(List<ConfigBean> configs) {
    JSONObject configJson = new JSONObject();

    for (int i = 0; i < configs.size(); i++) {
      configJson.put(configs.get(i).getName(),configs.get(i).getValue());
    }

    String appId = configJson.getString("app_id");
    String appSecret = configJson.getString("app_secret");
    String token = configJson.getString("token");
    String domain = configJson.getString("domain");


    if (StringUtils.isNotBlank(appId) && StringUtils.isNotBlank(appSecret)) {
      weixin.setAppId(appId);
      weixin.setSecret(appSecret);
      weixin.setToken(token);
    }
  }

  public Weixin getObject() throws Exception {
    return weixin;
  }

  public Class<?> getObjectType() {
    return Weixin.class;
  }

  public boolean isSingleton() {
    return true;
  }
}
