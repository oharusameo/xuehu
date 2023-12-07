package com.harusame.template.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel(value = "删除分类请求对象")
@Data
public class DelCategoryDTO {
    @NotNull
    @ApiModelProperty(value = "分类id", example = "1")
    private Long categoryId;
}
