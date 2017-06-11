package com.dszshen.wxsdk.controller.weixin;

import com.dszshen.wxsdk.common.util.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/5/23 0023.
 */
@Controller
@RequestMapping("weixin/api")
public class MenusCtrl {

    @RequestMapping("getMenus")
    public JsonResult getMenus(){
        return JsonResult.success(null);
    }
}
