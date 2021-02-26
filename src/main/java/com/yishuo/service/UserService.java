package com.yishuo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
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

    /**
     * 分页查询。 mybatisplus 只支持对单表进行分页查询，不支持关联表分页查询
     *
     */

    IPage<User> ListPage (Map map);





}
