package com.dszshen.wxsdk.service.weixin;

import com.alibaba.fastjson.JSONObject;
import com.belerweb.social.weixin.bean.Message;
import com.dszshen.wxsdk.bean.ConfigBean;
import com.dszshen.wxsdk.bean.weixin.Weixin;
import com.dszshen.wxsdk.common.util.weixin.WeixinUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangbin on 2017/5/25 0025.
 */
@Service
public class WeixinService implements ConfigChanged,ApplicationListener<ContextRefreshedEvent> {

    //@Value("${weixin.token}")
    private String token;
    //@Value("${weixin.app_id}")
    private String appID;
    //@Value("${weixin.app_secret}")
    private String appSecret;

    @Resource
    private Weixin weixin;

    /**
     * 验证微信来源的有效性
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public boolean validate(String signature, String timestamp, String nonce) {
        System.out.println("token------------>:"+token);
        String[] chars = new String[]{token, timestamp, nonce};
        Arrays.sort(chars);
        String sha1 = DigestUtils.shaHex(StringUtils.join(chars));
        return sha1.equals(signature);
    }

    public boolean saveMessage(Message message) {
        //boolean success = weixinMpMessageDao.insert(message) == 1;
        //synchronized (this) {
            //notify();
        //}
        //LOGGER.debug("notify mp message response process.");
        return true;
    }


    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //this.context = contextRefreshedEvent;
    }

    public void configChanged(List<ConfigBean> configs) {

    }
}
