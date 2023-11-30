package com.harusame.template.api;

import com.harusame.template.domain.dto.LoginDTO;
import com.harusame.template.domain.dto.RegisterDTO;
import com.harusame.template.domain.pojo.Result;
import com.harusame.template.mapper.AdminUserMapper;
import com.harusame.template.service.AdminUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@Api(tags = "管理员模块")
@RestController
@RequestMapping("/admin-center/api/v1/adminUser")
public class AdminUserApi {
    @Resource
    private AdminUserService adminUserService;

    @PostMapping("/login")
    @ApiOperation("管理员登录接口")
    public Result login(@Valid @RequestBody LoginDTO loginDTO) {
        String token = adminUserService.login(loginDTO);
        return Result.success(token);
    }

    @PostMapping("/register")
    @ApiOperation("管理员注册接口")
    public Result register(@Valid @RequestBody RegisterDTO registerDTO) {
        String token = adminUserService.register(registerDTO);
        return Result.success("注册成功,即将跳转登录页面", token);
    }

}
