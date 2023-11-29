package com.harusame.template.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel("新增频道请求对象")
@Data
public class AddChannelDTO {
    @NotBlank(message = "频道名称不能为空")
    @ApiModelProperty(value = "频道名称", example = "砍口垒")
    private String name;
    @NotBlank(message = "频道描述不能为空")
    @ApiModelProperty(value = "频道描述", example = "玩砍口垒玩的")
    private String description;

}
