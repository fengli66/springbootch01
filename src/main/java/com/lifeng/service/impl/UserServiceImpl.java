package com.lifeng.service.impl;

import com.lifeng.pojo.User;
import com.lifeng.repository.UserRespository;
import com.lifeng.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * 用户的业务逻辑层接口实现类
 *
 * @author fengli
 * @version 1.0
 * @date 2020/12/05
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRespository userRespository;

    @Override
    public Optional<User> findById(String id) {
        return userRespository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRespository.findAll();
    }

    @Override
    public User save(User user) {
        return userRespository.save(user);
    }

    @Override
    public void delete(User user) {
        userRespository.delete(user);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRespository.findAll(pageable);
    }

    @Override
    public List<User> findByName(String name) {
        return userRespository.findByUsername(name);
    }

    @Override
    public List<User> findByUserNameLike(String name) {
        return userRespository.findByUsernameLike(name);
    }

    @Override
    public List<User> findByIdIn(Collection<String> ids) {
        return userRespository.findByUserIDIn(ids);
    }
}
