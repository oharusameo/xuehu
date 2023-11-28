package com.harusame.template.service;

import com.harusame.template.domain.dto.LoginDTO;
import com.harusame.template.domain.dto.RegisterDTO;
import com.harusame.template.domain.pojo.AdminUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author ggzst
* @description 针对表【admin_user(管理员用户信息表)】的数据库操作Service
* @createDate 2023-11-28 18:52:17
*/
public interface AdminUserService extends IService<AdminUser> {

    String login(LoginDTO loginDTO);

    String register(RegisterDTO registerDTO);
}
