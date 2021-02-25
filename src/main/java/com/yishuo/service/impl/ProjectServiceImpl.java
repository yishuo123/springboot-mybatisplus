package com.yishuo.service.impl;

import com.yishuo.entity.Project;
import com.yishuo.mapper.ProjectMapper;
import com.yishuo.service.ProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2021-02-25
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

}
