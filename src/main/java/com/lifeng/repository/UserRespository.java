package com.lifeng.repository;

import com.lifeng.pojo.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * 用户的Respository
 *
 * @author fengli
 * @version 1.0
 * @date 2020/12/05
 * JpaRepositorγ 接口是不开启事务的，而
 * SimpleJapRepository 默认是开启事务的
 */
@Repository
public interface UserRespository extends JpaRepository<UserLogin, String> {
    /**
     * 通过名字查询用户信息
     */
    List<UserLogin> findByUsername(String username);
    /**
     * 通过名字like查询，参数为name
     *
     */
    List<UserLogin> findByUsernameLike(String name);

    /**
     * 通过主键id集合查询，参数为id集合
     * @param ids
     * @return
     */
    List<UserLogin> findByUserIDIn(Collection<String> ids);
}

