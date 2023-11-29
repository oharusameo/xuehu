package com.harusame.template.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("频道顺序列表")
public class ChannelOrderListDTO {
    @ApiModelProperty(value = "id", example = "1")
    @NotNull
    private Integer id;
    @ApiModelProperty(value = "顺序", example = "1")
    @NotNull
    private Integer orderNum;

}
