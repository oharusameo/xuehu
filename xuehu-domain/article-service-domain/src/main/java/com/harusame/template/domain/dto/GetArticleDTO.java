package com.harusame.template.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel("获取文章内容请求对象")
public class GetArticleDTO implements Serializable {
    @NotNull
    @ApiModelProperty(value = "文章id",example = "1")
    private Long articleId;
}
