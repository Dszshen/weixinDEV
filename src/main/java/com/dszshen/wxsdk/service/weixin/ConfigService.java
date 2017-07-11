package com.dszshen.wxsdk.service.weixin;

import com.alibaba.fastjson.JSONObject;
import com.dszshen.wxsdk.dao.ConfigDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 配置service:微信配置，数据库配置，七牛配置等
 *
 * Created by zhangbin on 2017/7/11 0011.
 */
@Service("configService")
public class ConfigService implements InitializingBean, ApplicationContextAware {

    private String version;
    private long versionTime;

    /*
        当使用final修饰基本类型变量时，不能对基本类型变量重新赋值，
        因此基本类型变量不能被改变。
        但对于引用类型的变量而言，它保存的仅仅是一个引用
        ，final只保证这个引用所引用的地址不会改变，即一直引用同一个对象，
        但这个对象完全可以发生改变。
    */
    private static final JSONObject CONFIG = new JSONObject();

    @Resource
    private ConfigDao configDao;

    @Value("${spring.profiles.active}")
    private String profile;

    //七牛云存储
    @Value("${qiniu.access_key}")
    private String qiniuAccesskey;
    @Value("${qiniu.secret_key}")
    private String qiniuSecretkey;
    @Value("${qiniu.bucket}")
    private String qiniuBucket;
    @Value("${qiniu.prefix}")
    private String qiniuPrefix;

    //微信相关
    @Value("${weixin.app_id}")
    private String weixinAppId;
    @Value("${weixin.app_secret}")
    private String weixinAppSecret;
    @Value("${weixin.token}")
    private String weixinToken;
    @Value("${weixin.id}")
    private String weixinId;
    @Value("${weixin.aes}")
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

    public void setConfigs(JSONObject configs) {
        configDao.insertAll(configs);//添加或者更新配置项
        CONFIG.clear();
        getConfig();
        //notifyChanged();
    }

    public JSONObject getConfig() {
        if (CONFIG.isEmpty()) {
            List<JSONObject> configList = configDao.selectAllConfig();

            for (JSONObject config : configList) {
                String key = config.getString("name");
                //if (profile.equalsIgnoreCase("dev")) {
                // To distinguish multi wx dev account
                //CONFIG.put(key, System.getProperty(key));
                //} else {
                CONFIG.put(key, config.get("value"));
                //}
            }
        }
        return CONFIG;
    }

    public void afterPropertiesSet() throws Exception {
        JSONObject configs = new JSONObject();
        List<JSONObject> configList = configDao.selectAllConfig();

        for (JSONObject config : configList) {
            configs.put(config.getString("name"), config.getString("value"));
        }

        configs.put("version", version);
        configs.put("version_time", versionTime);
        //configs.put("profile", profile);

        if (StringUtils.isBlank(configs.getString("qiniu_access_key"))) {
            configs.put("qiniu_access_key", qiniuAccesskey);
        }
        if (StringUtils.isBlank(configs.getString("qiniu_secret_key"))) {
            configs.put("qiniu_secret_key", qiniuSecretkey);
        }
        if (StringUtils.isBlank(configs.getString("qiniu_bucket"))) {
            configs.put("qiniu_bucket", qiniuBucket);
        }
        if (StringUtils.isBlank(configs.getString("qiniu_prefix"))) {
            configs.put("qiniu_prefix", qiniuPrefix);
        }

        if (StringUtils.isBlank(configs.getString("weixin_app_id"))) {
            configs.put("weixin_app_id", weixinAppId);
        }
        if (StringUtils.isBlank(configs.getString("weixin_app_secret"))) {
            configs.put("weixin_app_secret", weixinAppSecret);
        }
        if (StringUtils.isBlank(configs.getString("weixin_token"))) {
            configs.put("weixin_token", weixinToken);
        }
        if (StringUtils.isBlank(configs.getString("weixin_id"))) {
            configs.put("weixin_id", weixinId);
        }
        if (StringUtils.isBlank(configs.getString("weixin_aes"))) {
            configs.put("weixin_aes", weixinAES);
        }
        setConfigs(configs);
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }


}
