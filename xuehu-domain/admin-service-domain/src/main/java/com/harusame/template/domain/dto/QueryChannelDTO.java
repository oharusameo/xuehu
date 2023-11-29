package com.harusame.template.domain.dto;

import com.harusame.template.common.enums.ChannelStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("查询频道请求对象")
public class QueryChannelDTO extends PageDTO {
    @ApiModelProperty(value = "频道名称", example = "砍口垒")
    private String name;
    @ApiModelProperty(value = "频道状态", example = "AVAILABLE")
    private ChannelStatusEnum channelStatusEnum;


}
