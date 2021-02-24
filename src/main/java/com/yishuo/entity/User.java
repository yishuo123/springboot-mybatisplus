package com.yishuo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @Author
 * @Date 2021/2/23 19:10
 * @Version V1.0
 * @Description: 用户表
 */


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)  // 开启链式调用
@TableName("t_user")
public class User {
    private int id;
    private String password;
    private String userName;
    private String phone;

}
