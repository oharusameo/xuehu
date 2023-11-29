package com.harusame.template.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harusame.template.common.enums.UserStatusEnum;
import com.harusame.template.common.utils.IpUtils;
import com.harusame.template.common.utils.TokenUtils;
import com.harusame.template.domain.dto.LoginDTO;
import com.harusame.template.domain.dto.RegisterDTO;
import com.harusame.template.domain.pojo.AdminUser;
import com.harusame.template.exception.BusinessException;
import com.harusame.template.service.AdminUserService;
import com.harusame.template.mapper.AdminUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author ggzst
 * @description 针对表【admin_user(管理员用户信息表)】的数据库操作Service实现
 * @createDate 2023-11-28 18:52:17
 */
@Service
@Slf4j
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser>
        implements AdminUserService {

    @Resource
    private AdminUserMapper adminUserMapper;
    @Resource
    private TokenUtils tokenUtils;

    @Override
    public String login(LoginDTO loginDTO) {
        AdminUser user = this.getOne(new QueryWrapper<AdminUser>().eq("username", loginDTO.getUsername())
                .select("id", "username", "password", "salt", "status"));
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        if (user.getStatus() == UserStatusEnum.UNAVAILABLE) {
            throw new BusinessException("用户已被禁用");
        }
        String salt = user.getSalt();
        String password = DigestUtil.md5Hex(loginDTO.getPassword() + salt);
        if (!user.getPassword().equals(password)) {
            throw new BusinessException("密码错误");
        }
        String addr = IpUtils.getIpAddr();
        log.info("登录成功，用户：{}，地址：{}", user.getUsername(), addr);
        AdminUser adminUser = new AdminUser();
        adminUser.setId(user.getId());
        adminUser.setLoginTime(new Date());
        adminUserMapper.updateById(adminUser);
        return tokenUtils.loginAndGetToken(user.getId());
    }

    @Override
    public String register(RegisterDTO registerDTO) {
        AdminUser adminUser = new AdminUser();
        String salt = RandomUtil.randomNumbers(8);
        String password = DigestUtil.md5Hex(registerDTO.getPassword() + salt);
        adminUser.setUsername(registerDTO.getUsername());
        adminUser.setPassword(password);
        adminUser.setSalt(salt);
        adminUser.setEmail(registerDTO.getEmail());
        adminUser.setPhone(registerDTO.getPhone());
        adminUser.setStatus(UserStatusEnum.AVAILABLE);
        adminUserMapper.insert(adminUser);
        return tokenUtils.loginAndGetToken(adminUser.getId());
    }
}




