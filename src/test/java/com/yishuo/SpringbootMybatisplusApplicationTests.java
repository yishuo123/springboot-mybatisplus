package com.yishuo;

import com.yishuo.dao.UserDao;
import com.yishuo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbootMybatisplusApplicationTests {

    @Autowired
    UserDao userDao;

    @Test
    void contextLoads() {
        List<User> users = userDao.selectList(null);
        users.forEach(user -> System.out.println(user.getUserName()));
    }

}
