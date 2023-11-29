package com.harusame.template.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.harusame.template.domain.dto.AddChannelDTO;
import com.harusame.template.domain.dto.ChangeChannelOrderDTO;
import com.harusame.template.domain.dto.ChangeChannelStatusDTO;
import com.harusame.template.domain.dto.QueryChannelDTO;
import com.harusame.template.domain.pojo.AdminChannel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.harusame.template.domain.pojo.PageResult;

/**
* @author ggzst
* @description 针对表【admin_channel(频道信息表)】的数据库操作Service
* @createDate 2023-11-28 18:52:16
*/
public interface AdminChannelService extends IService<AdminChannel> {

    PageResult<AdminChannel> queryPage(QueryChannelDTO queryChannelDTO);

    void addChannel(AddChannelDTO addChannelDTO);

    void changeChannelStatus(ChangeChannelStatusDTO changeChannelStatusDTO);

    void changeChannelOrder(ChangeChannelOrderDTO changeChannelOrderDTO);
}
