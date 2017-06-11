package com.dszshen.wxsdk.dao;

import com.dszshen.wxsdk.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author zhangbin
 * @version 1.0.0
 *
 * 1. Repository 是一个空接口. 即是一个标记接口
 * 2. 若我们定义的接口继承了 Repository, 则该接口会被 IOC 容器识别为一个 Repository Bean.
 * 纳入到 IOC 容器中. 进而可以在该接口中定义满足一定规范的方法.
 * 3. 实际上, 也可以通过 @RepositoryDefinition 注解来替代继承 Repository 接口
 *
 * 用户Dao接口
 */

public interface UserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

    @Query("select u from User u where username=:userName")
    public User getUserByUsername(@Param("userName") String userName);


}

