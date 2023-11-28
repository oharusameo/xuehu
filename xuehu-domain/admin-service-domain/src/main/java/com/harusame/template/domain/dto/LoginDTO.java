package com.harusame.template.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel("登录请求对象")
public class LoginDTO {
    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名", example = "harusame")
    private String username;
    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码", example = "123")
    private String password;
}
