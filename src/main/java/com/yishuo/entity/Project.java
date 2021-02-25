package com.yishuo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
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
@TableName("t_project")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 项目编号
     */
    private String projectNumber;

    /**
     * 项目名称
     */
    private String name;

    private String operator;

    private LocalDate opedate;


}
