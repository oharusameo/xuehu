package com.harusame.template.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel("删除图片请求对象")
@Data
public class DelImageDTO {
    @ApiModelProperty(value = "分类id", example = "1")
    @NotNull
    private Long categoryId;
    @ApiModelProperty(value = "图片id列表", example = "[1,2,3]")
    @NotNull
    private List<Integer> delImageIds;
}
