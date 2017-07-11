package com.dszshen.wxsdk.dao;

import com.alibaba.fastjson.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by zhangbin on 2017/7/11 0011.
 */
public interface ConfigDao extends JpaRepository{


    public List<JSONObject> selectAllConfig();

    public void insertAll(JSONObject jsonObject);
}
