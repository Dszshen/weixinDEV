package com.dszshen.wxsdk.service.weixin;

import com.dszshen.wxsdk.bean.ConfigBean;

import java.util.List;

/**
 * Created by zhangbin on 2017/7/11 0011.
 */
public interface ConfigChanged {
    void configChanged(List<ConfigBean> configs);
}
