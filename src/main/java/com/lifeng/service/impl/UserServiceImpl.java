package com.lifeng.service.impl;

import com.lifeng.pojo.UserLogin;
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
    public Optional<UserLogin> findById(String id) {
        return userRespository.findById(id);
    }

    @Override
    public List<UserLogin> findAll() {
        return userRespository.findAll();
    }

    @Override
    public UserLogin save(UserLogin user) {
        return userRespository.save(user);
    }

    @Override
    public void delete(UserLogin user) {
        userRespository.delete(user);
    }

    @Override
    public Page<UserLogin> findAll(Pageable pageable) {
        return userRespository.findAll(pageable);
    }

    @Override
    public List<UserLogin> findByName(String name) {
        return userRespository.findByUsername(name);
    }

    @Override
    public List<UserLogin> findByUserNameLike(String name) {
        return userRespository.findByUsernameLike(name);
    }

    @Override
    public List<UserLogin> findByIdIn(Collection<String> ids) {
        return userRespository.findByUserIDIn(ids);
    }
}
