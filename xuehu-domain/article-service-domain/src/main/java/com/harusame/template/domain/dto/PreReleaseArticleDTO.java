package com.harusame.template.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel("预发布文章请求对象")
public class PreReleaseArticleDTO {
    @NotBlank
    @ApiModelProperty(value = "文章标题", example = "标题")
    private String articleTitle;
    @NotBlank
    @ApiModelProperty(value = "文章内容", example = "内容")
    private String content;
}
