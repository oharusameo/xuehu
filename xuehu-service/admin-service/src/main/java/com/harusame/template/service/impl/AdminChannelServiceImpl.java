package com.harusame.template.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harusame.template.domain.pojo.AdminChannel;
import com.harusame.template.service.AdminChannelService;
import com.harusame.template.mapper.AdminChannelMapper;
import org.springframework.stereotype.Service;

/**
* @author ggzst
* @description 针对表【admin_channel(频道信息表)】的数据库操作Service实现
* @createDate 2023-11-28 18:52:16
*/
@Service
public class AdminChannelServiceImpl extends ServiceImpl<AdminChannelMapper, AdminChannel>
    implements AdminChannelService{

}




