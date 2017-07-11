package com.dszshen.wxsdk.controller.weixin;

import com.alibaba.fastjson.JSONObject;
import com.dszshen.wxsdk.common.util.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhangbin on 2017/5/23 0023.
 */
@Controller
@RequestMapping("weixin/api")
public class MenusCtrl {

    @RequestMapping(method = RequestMethod.GET,value = "menu")
    public JsonResult getMenus(){
        return JsonResult.success(null);
    }

    @RequestMapping(method = RequestMethod.POST,value = "menu")
    @ResponseBody
    public JsonResult addMenu(@RequestBody JSONObject menus){

        return JsonResult.success(null);
    }
}
