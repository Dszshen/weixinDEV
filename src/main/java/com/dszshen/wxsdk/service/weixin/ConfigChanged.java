package com.dszshen.wxsdk.service.weixin;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by zhangbin on 2017/7/11 0011.
 */
public interface ConfigChanged {
    void configChanged(JSONObject config);
}
