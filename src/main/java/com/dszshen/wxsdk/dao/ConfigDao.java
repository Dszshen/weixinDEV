package com.dszshen.wxsdk.dao;

import com.alibaba.fastjson.JSONObject;
import com.dszshen.wxsdk.bean.ConfigBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhangbin on 2017/7/11 0011.
 */
public interface ConfigDao extends JpaRepository<ConfigBean, String>, JpaSpecificationExecutor<ConfigBean> {


    //public List<JSONObject> selectAllConfig();
    /*@Transactional
    @Modifying
    @Query(value="INSERT INTO weixin_config VALUES (:name,:value)",nativeQuery = true)
    public void insertAll(@Param("name") String name,@Param("value") String value);*/

    @Transactional
    @Modifying
    @Query(value="INSERT INTO weixin_config VALUES (:name,:value)",nativeQuery = true)
    public void insertOne(@Param("name") String name,@Param("value") String value);
}
