package com.iamkyun.playground.course.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@ApiModel("课程")
public class CourseRequest {
    @ApiModelProperty("名称")
    @Size(max = 100, message = "name最大不能超过100字符")
    @NotBlank(message = "name不能为空")
    private String name;

    @ApiModelProperty("描述")
    @Size(max = 500, message = "description最大不能超过500字符")
    @NotBlank(message = "description不能为空")
    private String description;
}
