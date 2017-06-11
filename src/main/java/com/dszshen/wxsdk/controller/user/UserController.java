package com.dszshen.wxsdk.controller.user;

import com.dszshen.wxsdk.bean.User;
import com.dszshen.wxsdk.common.util.JsonResult;
import com.dszshen.wxsdk.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangbin on 2016/6/26.
 * 用户管理
 *
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET,value="add")
    public JsonResult addUser(@RequestParam("user") User user){

        User u = userService.add(user);

        return JsonResult.success(u);
    }

    @RequestMapping(method = RequestMethod.GET,value="list")
    public JsonResult list(){
        return JsonResult.success(userService.findAll());
    }

}
