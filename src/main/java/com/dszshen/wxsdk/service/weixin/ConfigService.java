package com.dszshen.wxsdk.service.weixin;

import com.alibaba.fastjson.JSONObject;
import com.dszshen.wxsdk.bean.ConfigBean;
import com.dszshen.wxsdk.dao.ConfigDao;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 配置service:微信配置，数据库配置，七牛配置等
 * <p>
 * Created by zhangbin on 2017/7/11 0011.
 */
@Service("configService")
public class ConfigService implements InitializingBean, ApplicationContextAware {

  private static final Logger LOGGER = LoggerFactory.getLogger(ConfigService.class);
  private String version;
  private long versionTime;

  /*
      当使用final修饰基本类型变量时，不能对基本类型变量重新赋值，
      因此基本类型变量不能被改变。
      但对于引用类型的变量而言，它保存的仅仅是一个引用
      ，final只保证这个引用所引用的地址不会改变，即一直引用同一个对象，
      但这个对象完全可以发生改变。
  */
  private static final List<ConfigBean> CONFIG = new ArrayList<ConfigBean>();

  @Resource
  private ConfigDao configDao;

  //@Value("#[spring.profiles.active}")
  //private String profile;

  //七牛云存储
  @Value("#[qiniu.access_key:]")
  private String qiniuAccesskey;
  @Value("#[qiniu.secret_key:]")
  private String qiniuSecretkey;
  @Value("#[qiniu.bucket:]")
  private String qiniuBucket;
  @Value("#[qiniu.prefix:]")
  private String qiniuPrefix;

  //微信相关
  @Value("#[weixin.app_id:]")
  private String weixinAppId;
  @Value("#[weixin.app_secret:]")
  private String weixinAppSecret;
  @Value("#[weixin.token:]")
  private String weixinToken;
  @Value("#[weixin.id:]")
  private String weixinId;
  @Value("#[weixin.aes:]")
  private String weixinAES;

  private ApplicationContext context;

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public long getVersionTime() {
    return versionTime;
  }

  public void setVersionTime(long versionTime) {
    this.versionTime = versionTime;
  }

  public void setConfigs(List<ConfigBean> configs) {
    /*for (int i = 0; i < configs.size(); i++) {
      configDao.insertOne(configs.get(i).getName(), configs.get(i).getValue());//添加或者更新配置项
    }*/
    CONFIG.clear();
    getConfig();
    notifyChanged();
  }

  private void notifyChanged() {
    Map<String, ConfigChanged> beans = context.getBeansOfType(ConfigChanged.class);
    LOGGER.debug("Find {} beans for listenning ConfigChanged.", beans.size());
    List<ConfigBean> configs = getConfig();
    for (ConfigChanged listener : beans.values()) {
      listener.configChanged(configs);
    }
  }

  public List<ConfigBean> getConfig() {
    if (CONFIG.isEmpty()) {
      //查询所有的配置
      List<ConfigBean> configList = configDao.findAll();
      for (ConfigBean config : configList) {
        CONFIG.add(config);
      }
    }
    return CONFIG;
  }

  public void afterPropertiesSet() throws Exception {
    List<ConfigBean> configs = new ArrayList<ConfigBean>();
    List<ConfigBean> configList = configDao.findAll();
    for (ConfigBean config : configList) {
      configs.add(config);
    }
    System.out.println("weixinAppId------------->:"+weixinAppId);
    System.out.println("qiniuAccesskey------------->:"+qiniuAccesskey);
    setConfigs(configs);
  }

  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.context = applicationContext;
  }

}
