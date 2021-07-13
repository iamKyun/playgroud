package com.iamkyun.playground.course.controller;

import com.iamkyun.playground.course.service.CourseService;
import com.iamkyun.playground.credit.api.CreditClient;
import com.iamkyun.playground.tools.Authentication;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping
public class CourseController {

    @Autowired
    CourseService courseService;

    @Autowired
    CreditClient creditClient;

    /**
     * 点击章节学习，获取课程章节的信息
     * 如果是第一次学习，初始化用户的课程
     */
    @GetMapping("/courses/{courseId}/chapters/{chapterId}")
    public Boolean getCourseChapterInfo(@PathVariable @ApiParam("课程ID") Long courseId,
                                      @PathVariable @ApiParam("章节ID") Long chapterId) {
        // 当前用户
        Long userId = Authentication.getCurrentUserId();
        // 查询章节信息

        // 查询用户学习进度

        // 首次进入？创建学习进度

        return true;
    }

    /**
     * V1版本：全部用数据库更新存储
     *
     * @param courseId  课程ID
     * @param chapterId 章节ID
     * @param progress  根据不同课件，progress含义不同，视频课件是视频的时间信息，文件课件是文件页数/段落信息
     */
    @ApiOperation("保存学习记录")
    @PostMapping("/v1/courses/{courseId}/chapters/{chapterId}/log")
    public Boolean saveLearningRecord(@PathVariable @ApiParam("课程ID") Long courseId,
                                      @PathVariable @ApiParam("章节ID") Long chapterId,
                                      @RequestParam @ApiParam("课件进度") Long progress) {
        // 当前用户
        Long userId = Authentication.getCurrentUserId();
        // 查询之前的学习进度

        // 计算当前学习进度

        // 更新章节学习进度

        // 更新课程学习进度

        // 调用积分接口
        return true;
    }
}
