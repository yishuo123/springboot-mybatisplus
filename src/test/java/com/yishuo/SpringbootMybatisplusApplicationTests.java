package com.yishuo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yishuo.mapper.UserMapper;
import com.yishuo.entity.User;
import com.yishuo.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbootMybatisplusApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserServiceImpl userService;

    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        users.forEach(user -> System.out.println(user.getUserName()));
    }

    @Test
    void  test(){
        IPage<User> userIPage = userService.ListPage(null);

        long total = userIPage.getTotal();
        System.out.println("total>>>>>>>>" + total);

        List<User> records = userIPage.getRecords();
        records.forEach(user -> System.out.println(user));

    }




}
