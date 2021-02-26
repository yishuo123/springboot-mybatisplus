package com.yishuo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2021-02-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单路径
     */
    private String url;

    /**
     * 节点等级 1：一级节点；2：二级节点
     */
    private Integer level;

    /**
     * 父节点ID
     */
    private String parentId;

    /**
     * 状态： 1 ：启用；2：禁用；
     */
    private Integer status;

    private String remark;

    /**
     * 菜单显示排序  一级菜单 1,2,3... 二级菜单10,11,12
     */
    private Integer displayOrder;

    /**
     * 菜单图标
     */
    private String menuIcon;


}
