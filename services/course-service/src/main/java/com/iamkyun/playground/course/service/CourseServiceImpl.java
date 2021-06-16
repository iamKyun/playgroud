package com.iamkyun.playground.course.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iamkyun.playground.course.entity.Course;
import com.iamkyun.playground.course.mapper.CourseMapper;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

}
