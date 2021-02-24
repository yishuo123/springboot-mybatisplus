package com.yishuo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yishuo.dao.UserDao;
import com.yishuo.entity.User;
import com.yishuo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author lizy
 * @Date 2021/2/24 19:03
 * @Version V1.0
 * @Description:
 */
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao  userDao;


    /**
     *  queryWrapper  里面的查询很多
     * @param map
     * @return
     */
    @Override
    public List<User> selectList(HashMap map) {

        QueryWrapper  queryWrapper = new QueryWrapper();

        queryWrapper.eq("id","1");

        // lt 代表小于 le 小于等于  gt 大于  ge 大于等于
        queryWrapper.lt("id","99");

        List<User> users = userDao.selectList(queryWrapper);

        return users;
    }


    /**
     * 批量修改
     * @param map
     */
    @Override
    public void updata(Map map) {

        User user = new User();
        user.setUserName("123");// 设置修改的字段

        // 修改的条件 where 后面条件
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("phone","123");

        userDao.update(user, queryWrapper);
    }


}
