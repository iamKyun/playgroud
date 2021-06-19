package com.iamkyun.playground.course.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author Kyun
 * @since 2021-06-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Course", description = "课程表")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private LocalDateTime createTime;

    private Long views;

    private String description;
}
