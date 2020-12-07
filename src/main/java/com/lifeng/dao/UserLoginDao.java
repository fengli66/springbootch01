package com.lifeng.dao;

import com.lifeng.pojo.UserLogin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户DAO
 * @author fengli
 * @date 2020/12/07
 */
@Mapper
public interface UserLoginDao {
    /**
     * 通过用户名与密码查询用户
     * @param username
     * @param password
     * @return
     */
    UserLogin findByNameAndPassword(@Param("username") String username,@Param("password") String password);

}
