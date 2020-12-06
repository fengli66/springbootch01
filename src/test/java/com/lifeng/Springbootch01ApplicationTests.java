package com.lifeng;

import com.lifeng.pojo.User;
import com.lifeng.repository.UserRespository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
        List<User> users = jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setUserID(resultSet.getString("userID"));
                user.setUserName(resultSet.getString("userName"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        });
        System.out.println("查询成功");
        for (User user : users) {
            System.out.println("id:" + user.getUserID() + ";name:" + user.getUserName());

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
        System.out.println("sssss"+dataSource.getClass());
        //获取数据库连接
        Connection connection = dataSource.getConnection();
        //关闭连接
        connection.close();
    }

    @Resource
    private UserRespository userRespository;
    @Test
    public void testRepsitory(){
        //查询所有数据
        List<User> userList = userRespository.findAll();
        System.out.println("findAll():"+userList.size());
        //通过姓名查询
        List<User> users = userRespository.findByUsername("lifeng");
        System.out.println("findByName():"+users.size());
        Assert.isTrue(users.get(0).getUserName().equals("lifeng"),"data error");
        //通过name模糊查询
        List<User> nameLike = userRespository.findByUsernameLike("%fen%");
        System.out.println("findByUserNameLike():"+nameLike.size());
        Assert.isTrue(nameLike.get(0).getUserName().equals("lifeng"),"data error");
        //通过id列表查询数据
        List<String> ids=new ArrayList<>();
        ids.add("1");
        ids.add("2");
        List<User> users1 = userRespository.findByUserIDIn(ids);
        System.out.println("findByIdIn():"+users1.size());
        //分页查询
//        Sort sort = Sort.by(Sort.Direction.DESC, "blogs.size");
        PageRequest pageRequest=PageRequest.of(0,10);
        Page<User> userPage = userRespository.findAll(pageRequest);
        System.out.println("page findAll():"+userPage.getTotalPages()+"/"+userPage.getSize());
        //新增数据
        User user=new User("2","test","123456");
        User save = userRespository.save(user);
        System.out.println("save"+save);
        //删除数据
//        userRespository.delete(user);


    }

}
