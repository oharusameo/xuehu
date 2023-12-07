package com.harusame.template.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "新增分类请求对象")
public class AddCategoryDTO {
    @NotBlank(message = "分类名称不能为空")
    @ApiModelProperty(value = "分类名称", example = "砍口垒")
    private String categoryName;
}
