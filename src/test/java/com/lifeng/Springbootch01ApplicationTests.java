package com.lifeng;

import com.lifeng.dao.UserLoginDao;
import com.lifeng.pojo.UserLogin;
import com.lifeng.repository.UserRespository;
import com.lifeng.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试类
 */
@RunWith(SpringRunner.class)  //参数化运行器
@SpringBootTest
class Springbootch01ApplicationTests {

    @Test
        //Junit单元测试的注解
    void contextLoads() {
    }

    /*sprin boot start be 中包含的 pring-jdbc 包， 我们可以通过这个 具类对数
据库进行增、删、改、查等操作。*/
    @Resource  //自动注入
    private JdbcTemplate jdbcTemplate;

    /**
     * Mysql集成Spring boot简单测试
     */
    @Test
    public void mySqlTest() {
        String sql = "SELECT userid,username,password FROM userlogin";
        //RowMapper 可以将查询出的每 行数据封装成用户定义的类
        List<UserLogin> users = jdbcTemplate.query(sql, new RowMapper<UserLogin>() {
            @Override
            public UserLogin mapRow(ResultSet resultSet, int i) throws SQLException {
                UserLogin user = new UserLogin();
                user.setUserID(resultSet.getString("userID"));
                user.setUsername(resultSet.getString("userName"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        });
        System.out.println("查询成功");
        for (UserLogin user : users) {
            System.out.println("id:" + user.getUserID() + ";name:" + user.getUsername());

        }
    }

    /**
     * springboot原来的连接池
     */
    @Autowired
    private DataSource dataSource;

    @Test
    public void datasourceTest() throws Exception {
        //查看默认数据源
        System.out.println("sssss" + dataSource.getClass());
        //获取数据库连接
        Connection connection = dataSource.getConnection();
        //关闭连接
        connection.close();
    }

    @Resource
    private UserRespository userRespository;

    @Resource
    private UserService userService;

    /**
     * 数据库操作相关测试
     */
    @Test
    public void testRepsitory() {
        //查询所有数据
        List<UserLogin> userList = userRespository.findAll();
        System.out.println("findAll():" + userList.size());
        //通过姓名查询
        List<UserLogin> users = userRespository.findByUsername("lifeng");
        System.out.println("findByName():" + users.size());
        Assert.isTrue(users.get(0).getUsername().equals("lifeng"), "data error");
        //通过name模糊查询
        List<UserLogin> nameLike = userRespository.findByUsernameLike("%fen%");
        System.out.println("findByUserNameLike():" + nameLike.size());
        Assert.isTrue(nameLike.get(0).getUsername().equals("lifeng"), "data error");
        //通过id列表查询数据
        List<String> ids = new ArrayList<>();
        ids.add("1");
        ids.add("2");
        List<UserLogin> users1 = userRespository.findByUserIDIn(ids);
        System.out.println("findByIdIn():" + users1.size());
        //分页查询
//        Sort sort = Sort.by(Sort.Direction.DESC, "blogs.size");
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<UserLogin> userPage = userRespository.findAll(pageRequest);
        System.out.println("page findAll():" + userPage.getTotalPages() + "/" + userPage.getSize());
        //新增数据
        UserLogin user = new UserLogin("2", "test", "123456");
        UserLogin save = userRespository.save(user);
        System.out.println("save" + save);
        //删除数据
//        userRespository.delete(user);
    }

    /**
     * 事务测试
     */
    @Test
    public void testTransaction() {
        UserLogin userLogin = new UserLogin("7", "132", "123456");
        userService.save(userLogin);
    }

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private StringRedisTemplate stringRedisTemplate; //StringRedisTemplate 只针对键值是字符串数据进行操作

    /**
     * redis测试
     */
    @Test
    public void testRedis(){
        //增 key:name, value:ay
        redisTemplate.opsForValue().set("name","lifeng");
        String name = (String) redisTemplate.opsForValue().get("name");
        System.out.println("setname:"+name);
        //删除
        redisTemplate.delete("name");
        String delname = (String) redisTemplate.opsForValue().get("name");
        System.out.println("delname:"+delname);
        //更新
        redisTemplate.opsForValue().set("name","lifeng2");
        String updetename = (String) redisTemplate.opsForValue().get("name");
        System.out.println("updetename:"+updetename);
        //查询
        String searchname = stringRedisTemplate.opsForValue().get("name");
        System.out.println("searchname:"+searchname);

    }

    /**
     * redis+springboot
     */
    @Test
    public void testFindById(){
        Long redisUserSize=0L;
        //查询id=3 的数据，该数据存在于Redis缓存中
        UserLogin userLogin=userService.findById("6");
        redisUserSize=redisTemplate.opsForList().size("ALL_USER_LIST");
        System.out.println("目前缓存中的用户数量为："+redisUserSize);
        System.out.println("------>>>id:"+userLogin.getUserID()+"name:"+userLogin.getUsername());
        //查询id=4
        UserLogin userLogin1=userService.findById("4");
        redisUserSize=redisTemplate.opsForList().size("ALL_USER_LIST");
        System.out.println("目前缓存中的用户数量为："+redisUserSize);
        System.out.println("------>>>id:"+userLogin1.getUserID()+"name:"+userLogin1.getUsername());
        //查询id=7 的数据，不存在redis缓存中，存在数据库中
        //所以会把在数据库中查询的数据更新到缓存中
        UserLogin userLogin2=userService.findById("7");
        System.out.println("------>>>id:"+userLogin2.getUserID()+"name:"+userLogin2.getUsername());
        redisUserSize=redisTemplate.opsForList().size("ALL_USER_LIST");
        System.out.println("目前缓存中的用户数量为："+redisUserSize);
    }

    /**
     * log4j测试
     *
     */
    @Test
    public void testLog4j(){
        Logger logger= LogManager.getLogger(this.getClass());
        UserLogin userLogin=new UserLogin("7",null,null);
        userService.delete(userLogin);
        logger.info("delete success!!!");

    }


    /**
     * mybatis测试
     */
    @Test
    public void testMybatis(){
        UserLogin userLogin = userService.findByNameAndPassword("132", "123456");
        Logger logger= LogManager.getLogger(this.getClass());
        if (userLogin != null) {
            logger.info("用户"+userLogin.getUsername()+userLogin.getPassword());
        }else {
            logger.info("没有该用户");
        }
    }
}
