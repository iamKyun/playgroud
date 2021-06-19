package com.iamkyun.playground.course.controller;


import com.iamkyun.playground.course.entity.Course;
import com.iamkyun.playground.course.model.CourseRequest;
import com.iamkyun.playground.course.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Kyun
 * @since 2021-06-19
 */
@Api(tags = "课程API")
@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @ApiOperation("查询所有课程")
    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return courseService.list();
    }

    @ApiOperation("查询课程")
    @GetMapping("/courses/{id}")
    public Course getCourseById(@PathVariable Long id) {
        return courseService.getById(id);
    }

    @ApiOperation("新增课程")
    @PostMapping("/courses")
    public Boolean saveCourse(@RequestBody @Valid CourseRequest courseRequest) {
        Course course = new Course();
        course.setCreateTime(LocalDateTime.now());
        course.setName(courseRequest.getName());
        course.setDescription(courseRequest.getDescription());
        return courseService.save(course);
    }

    @ApiOperation("更新课程")
    @PutMapping("/courses/{id}}")
    public Boolean saveCourse(@PathVariable Long id, @RequestBody @Valid CourseRequest courseRequest) {
        Course course = new Course();
        course.setId(id);
        course.setCreateTime(LocalDateTime.now());
        course.setName(courseRequest.getName());
        course.setDescription(courseRequest.getDescription());
        return courseService.updateById(course);
    }

    @ApiOperation("删除课程")
    @DeleteMapping("/courses/{id}}")
    public Boolean saveCourse(@PathVariable Long id) {
        return courseService.removeById(id);
    }

}
