package com.harusame.template.domain.dto;

import com.harusame.template.common.enums.ChannelStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("修改频道状态请求对象")
public class ChangeChannelStatusDTO {
    @NotNull
    @ApiModelProperty(value = "id", example = "1")
    private Integer id;
    @NotNull
    @ApiModelProperty(value = "频道状态", example = "AVAILABLE")
    private ChannelStatusEnum status;

}
