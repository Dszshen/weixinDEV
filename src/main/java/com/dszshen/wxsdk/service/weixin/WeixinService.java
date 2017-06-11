package com.dszshen.wxsdk.service.weixin;

import com.belerweb.social.weixin.bean.Message;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/5/25 0025.
 */
@Service
public class WeixinService {


    @Value("${weixin.token}")
    private String token;
    @Value("${weixin.appID}")
    private String appID;
    @Value("${weixin.appSecret}")
    private String appSecret;

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

}
