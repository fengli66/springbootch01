package com.lifeng.service;

import com.lifeng.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * 用户的业务逻辑层接口
 * @author fengli
 * @version 1.0
 * @date 2020/12/05
 */
public interface UserService {
    Optional<User> findById(String id);
    List<User> findAll();
    User save(User user);
    void delete(User user);
    //分页
    Page<User> findAll(Pageable pageable);
    /**
     * 通过名字查询用户信息
     */
    List<User> findByName(String name);
    /**
     * 通过名字like查询，参数为name
     *
     */
    List<User> findByUserNameLike(String name);

    /**
     * 通过主键id集合查询，参数为id集合
     * @param ids
     * @return
     */
    List<User> findByIdIn(Collection<String> ids);
}
