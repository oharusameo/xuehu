package com.harusame.template.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@ApiModel("注册请求对象")
public class RegisterDTO {
    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名", example = "harusame")
    private String username;
    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码", example = "123")
    private String password;
    @Email
    @ApiModelProperty(value = "邮箱", example = "ggzst321@outlook.com")
    private String email;
    @ApiModelProperty(value = "手机号码",example = "19875968027")
    @NotBlank
    @Pattern(regexp = "^0?(13|15|18|19)[0-9]{9}$", message = "手机格式不符合条件")
    private String phone;
}
