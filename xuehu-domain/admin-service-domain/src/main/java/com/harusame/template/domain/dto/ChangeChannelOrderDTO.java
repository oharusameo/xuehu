package com.harusame.template.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel("修改频道顺序请求对象")
public class ChangeChannelOrderDTO {
    @NotNull
    @ApiModelProperty(value = "频道顺序列表", example = "[{'id':55,'orderNum':2},{'id':59,'orderNum':3}]")
    List<ChannelOrderListDTO> channelOrderList;
}
