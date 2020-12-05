package com.lifeng;

import com.lifeng.pojo.UserLogin;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        List<UserLogin> userLogins = jdbcTemplate.query(sql, new RowMapper<UserLogin>() {
            @Override
            public UserLogin mapRow(ResultSet resultSet, int i) throws SQLException {
                UserLogin userLogin = new UserLogin();
                userLogin.setId(resultSet.getInt("userID"));
                userLogin.setUserName(resultSet.getString("userName"));
                userLogin.setPassword(resultSet.getString("password"));
                return userLogin;
            }
        });
        System.out.println("查询成功");
        for (UserLogin userLogin : userLogins) {
            System.out.println("id:" + userLogin.getId() + ";name:" + userLogin.getUserName());

        }


    }

}
