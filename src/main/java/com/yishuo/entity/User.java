package com.yishuo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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


    @TableId(value = "id",type = IdType.AUTO)   // 使用默认的就不用指定value值。 type 指定主键自增
    private int id;
    private String password;

    @TableField(value = "user_name")   // 数据库表中的列
    private String userName;
    private String phone;


    // 增加业务的属性   不映射数据中的任何字段
     @TableField(exist = false)
    private String  adb;

}
