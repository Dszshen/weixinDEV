package com.dszshen.wxsdk.service.weixin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.belerweb.social.http.HttpException;
import com.belerweb.social.weixin.bean.Message;
import com.dszshen.wxsdk.bean.ConfigBean;
import com.dszshen.wxsdk.bean.weixin.Menu;
import com.dszshen.wxsdk.bean.weixin.MenuType;
import com.dszshen.wxsdk.bean.weixin.Weixin;
import com.dszshen.wxsdk.common.util.http.Http;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.entity.StringEntity;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhangbin on 2017/5/25 0025.
 */
@Service
public class WeixinService implements ConfigChanged,ApplicationListener<ContextRefreshedEvent> {

    private String domain;

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
        System.out.println("token------------>:"+weixin.getToken());
        String[] chars = new String[]{weixin.getToken(), timestamp, nonce};
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
      //createMenu();
    }

    public void configChanged(List<ConfigBean> configs) {

    }

  /**
   * 更新微信菜单
   */
  public void createMenu() {
    JSONArray menus = new JSONArray();
    JSONArray subs = new JSONArray();
    JSONObject menu = null;

    subs = new JSONArray();
    JSONObject level1Menu = new JSONObject();
    level1Menu.put("name","主菜单1");
    menu = new JSONObject();
    menu.put("type","click");
    menu.put("name","子菜单1");
    menu.put("key","Huodong");
    subs.add(menu);
    level1Menu.put("sub_button",subs);
    menus.add(level1Menu);

    subs = new JSONArray();
    JSONObject profile = new JSONObject();
    profile.put("name","主菜单2");
    menu = new JSONObject();
    menu.put("type","click");
    menu.put("name","子菜单2");
    menu.put("key","profile");
    subs.add(menu);
    profile.put("sub_button",subs);
    menus.add(profile);

    JSONObject buttonObj = new JSONObject();

    buttonObj.put("button",menus);


    String menuJson = JSONObject.toJSONString(buttonObj);

    System.out.println("menuJSON------------->"+menuJson+"weixin.getAccessToken().getToken()-----"+weixin.getAccessToken().getToken());

    try {
      String json = Http.post("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + weixin.getAccessToken().getToken(), new StringEntity(menuJson, "UTF-8"));
      System.out.println("menuJSON-------json----post--------"+json);
    } catch (HttpException e) {
      e.printStackTrace();
      System.out.println("menuJSON-------error----post--------");
    }
    //Result<com.belerweb.social.bean.Error> result = weixin.getMenu().create(menus);
    //LOGGER.debug("Set weixin menu result {}: {}", result.success(), result.getError());
  }
}
