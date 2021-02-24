package com.yishuo.service;

import com.yishuo.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author
 * @Date 2021/2/24 19:01
 * @Version V1.0
 * @Description:
 */
public interface UserService {

    // 条件查询
    List<User> selectList (HashMap map);



    // 批量修改
    void updata(Map map);



}
