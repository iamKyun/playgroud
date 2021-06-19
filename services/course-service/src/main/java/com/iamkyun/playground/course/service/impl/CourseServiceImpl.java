package com.iamkyun.playground.course.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iamkyun.playground.course.entity.Course;
import com.iamkyun.playground.course.mapper.CourseMapper;
import com.iamkyun.playground.course.service.CourseService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Kyun
 * @since 2021-06-19
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

}
