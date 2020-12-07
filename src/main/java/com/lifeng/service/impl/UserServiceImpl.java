package com.lifeng.service.impl;

import com.lifeng.pojo.UserLogin;
import com.lifeng.repository.UserRespository;
import com.lifeng.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
//@Transactional 注解在类上,就意味着的所有public法都是开启事务
@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRespository userRespository;
    @Resource
    private RedisTemplate redisTemplate;
    private static final String ALL_USER="ALL_USER_LIST";

    Logger logger= LogManager.getLogger(this.getClass());
    /**
     * 根据id查询用户信息
     * 1、查询redis缓存中的所有数据
     * @param id
     * @return
     */
    @Override
    public UserLogin findById(String id) {
        //step.1 查询Redis缓存中的所有数据
        System.out.println("Ssss");
        List<UserLogin> userLodins = redisTemplate.opsForList().range(ALL_USER, 0, -1);
        if (userLodins != null && userLodins.size()>0) {
            for (UserLogin userLogin : userLodins) {
                if (userLogin.getUserID().equals(id)){
                    return userLogin;
                }
            }
        }
//        step2查询数据库的数据
        UserLogin userLogin = userRespository.findById(id).get();
        if (userLogin != null) {
            redisTemplate.opsForList().leftPush(ALL_USER,userLogin);
        }
        return userLogin;

    }

    @Override
    public List<UserLogin> findAll() {
        return userRespository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public UserLogin save(UserLogin user) {

        UserLogin save = userRespository.save(user);
        //出现空指针异常
       /* String error = null;
        error.equals("/");*/
        return save;
    }

    @Override
    public void delete(UserLogin user) {
        userRespository.delete(user);
        logger.info("userId:"+user.getUserID()+"用户被删除");

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

   /* @Override
    public UserLogin findOne(String id) {
        return userRespository.findOne(id);
    }*/
}
