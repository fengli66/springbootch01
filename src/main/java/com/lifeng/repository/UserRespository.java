package com.lifeng.repository;

import com.lifeng.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * 用户的Respository
 *
 * @author fengli
 * @version 1.0
 * @date 2020/12/05
 */
@Repository
public interface UserRespository extends JpaRepository<User, String> {
    /**
     * 通过名字查询用户信息
     */
    List<User> findByUsername(String username);
    /**
     * 通过名字like查询，参数为name
     *
     */
    List<User> findByUsernameLike(String name);

    /**
     * 通过主键id集合查询，参数为id集合
     * @param ids
     * @return
     */
    List<User> findByUserIDIn(Collection<String> ids);
}

