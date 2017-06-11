package com.dszshen.wxsdk.service.user;


import com.alibaba.fastjson.JSONObject;
import com.dszshen.wxsdk.bean.User;
import com.dszshen.wxsdk.common.util.JsonResult;
import com.dszshen.wxsdk.dao.UserDao;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author zhangbin
 */
@Service("userService")
public class UserService implements UserDetailsService {

    @Resource
    private UserDao userDao;

    /**
     * 通过Id查询user对象
     * @param userid
     * @return
     * @throws UsernameNotFoundException
     */
    public User loadUserByUsername(String userid) throws UsernameNotFoundException {
        User  user  = userDao.findOne(userid);
        return user;
    }

    /**
     * 登陆方法
     * @param userinfo：用户名和密码
     * @return
     */
    public JsonResult<JSONObject> login(JSONObject userinfo){
        JsonResult<JSONObject> loginInfo = new JsonResult<JSONObject>();
        JSONObject json = new JSONObject();
        if(!StringUtils.isEmpty(userinfo)){
            String username = userinfo.getString("username");
            String password = userinfo.getString("password");

            //对密码按照一定的规则加密
            //String pwd = 加密密码然后在进行判断--以后增加
            if(!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)){
                //从数据库中查询出的user
                User user = userDao.getUserByUsername(username);

                if(!StringUtils.isEmpty(user)){
                    if(password.equals(user.getPassword())){
                        user.setPassword("");//去掉密码，以免页面泄露密码
                        json.put("user",user);
                        loginInfo = JsonResult.success("登陆成功",json);
                    }else{
                        loginInfo = JsonResult.failure("用户名或者密码错误。",null);
                    }
                }else{
                    loginInfo = JsonResult.failure("用户不存在。",null);
                }
            }else{
                loginInfo = JsonResult.failure("对不起，用户名或者密码不能为空。",null);
            }
        }else if(StringUtils.isEmpty(userinfo)){
            loginInfo = JsonResult.failure("对不起，用户名或者密码不能为空。",null);
        }else{
            loginInfo = JsonResult.error("对不起，程序出错");
        }

        return loginInfo;
    }


    public User load(String id) {
        return null;
    }

    public User get(String id) {
        return null;
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public void persist(User entity) {

    }

    /**
     * 保存user并返回ID
     * @param user
     * @return
     */
    public String save(User user) {

        User u = userDao.save(user);
        return u.getId();
    }

    public User add(User user) {

        User u = userDao.save(user);
        return u;
    }

    public void saveOrUpdate(User entity) {

    }

    public void delete(String id) {

    }

    public void flush() {

    }
}

