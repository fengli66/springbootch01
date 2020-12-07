package com.lifeng.service;

import com.lifeng.pojo.UserLogin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * 用户的业务逻辑层接口
 *
 * @author fengli
 * @version 1.0
 * @date 2020/12/05
 */
public interface UserService {

    UserLogin findByNameAndPassword(String username,String password);
    UserLogin findById(String id);

    List<UserLogin> findAll();

    UserLogin save(UserLogin user);

    void delete(UserLogin user);

    //分页
    Page<UserLogin> findAll(Pageable pageable);

    /**
     * 通过名字查询用户信息
     */
    List<UserLogin> findByName(String name);

    /**
     * 通过名字like查询，参数为name
     */
    List<UserLogin> findByUserNameLike(String name);

    /**
     * 通过主键id集合查询，参数为id集合
     *
     * @param ids
     * @return
     */
    List<UserLogin> findByIdIn(Collection<String> ids);

//    UserLogin findOne(String id);
}
