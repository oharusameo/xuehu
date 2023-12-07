package com.harusame.template.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(value = "修改分类请求对象")
@Data
public class ModifyCategoryDTO {
    @NotNull
    @ApiModelProperty(value = "分类id", example = "1")
    private Long categoryId;
    @NotBlank
    @ApiModelProperty(value = "分类名称", example = "公主连结")
    private String categoryName;
}
