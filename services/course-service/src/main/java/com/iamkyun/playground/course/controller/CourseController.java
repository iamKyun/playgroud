package com.iamkyun.playground.course.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/courses")
public class CourseController {

    @ApiOperation("保存学习记录")
    @PostMapping("/{courseId}/chapters/{chapterId}/log")
    public Boolean saveLearningRecord(@PathVariable @ApiParam("课程ID") Long courseId,
                                      @PathVariable @ApiParam("章节ID") Long chapterId) {

        // 1. 获取当前

        return true;
    }
}
