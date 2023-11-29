package com.harusame.template.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harusame.template.common.utils.TokenUtils;
import com.harusame.template.domain.dto.*;
import com.harusame.template.domain.pojo.AdminChannel;
import com.harusame.template.domain.pojo.PageResult;
import com.harusame.template.exception.BusinessException;
import com.harusame.template.mapper.AdminChannelMapper;
import com.harusame.template.service.AdminChannelService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ggzst
 * @description 针对表【admin_channel(频道信息表)】的数据库操作Service实现
 * @createDate 2023-11-28 18:52:16
 */
@Service
public class AdminChannelServiceImpl extends ServiceImpl<AdminChannelMapper, AdminChannel>
        implements AdminChannelService {
    @Resource
    private AdminChannelMapper adminChannelMapper;
    @Resource
    private TokenUtils tokenUtils;

    @Override
    public PageResult<AdminChannel> queryPage(QueryChannelDTO queryChannelDTO) {
        queryChannelDTO.checkParam();
        QueryWrapper<AdminChannel> wrapper = new QueryWrapper<>();
        if (queryChannelDTO.getName() != null) {
            wrapper.like("name", queryChannelDTO.getName());
        }
        if (queryChannelDTO.getChannelStatusEnum() != null) {
            wrapper.eq("status", queryChannelDTO.getChannelStatusEnum());
        }
        wrapper.orderByAsc("order_num");
        Page<AdminChannel> pageCondition = new Page<>(queryChannelDTO.getPageIndex(), queryChannelDTO.getPageSize());
        Page<AdminChannel> page = this.page(pageCondition, wrapper);
        return new PageResult<>(
                queryChannelDTO.getPageIndex(),
                queryChannelDTO.getPageSize(),
                page.getTotal(),
                page.getRecords()
        );
    }

    @Override
    public void addChannel(AddChannelDTO addChannelDTO) {
        AdminChannel a = adminChannelMapper.selectChannelByName(addChannelDTO.getName());
        AdminChannel adminChannel = new AdminChannel();
        if (a != null) {
            if (a.getName() != null) {
                throw new BusinessException("频道名称已存在");
            }
            if (a.getOrderNum() != null) {
                adminChannel.setOrderNum(a.getOrderNum() + 1);
            }
        } else {
            adminChannel.setOrderNum(1);
        }
        String userId = tokenUtils.getUserIdFromHeader();
        BeanUtils.copyProperties(addChannelDTO, adminChannel);
        adminChannel.setCreatedUser(Integer.valueOf(userId));
        adminChannelMapper.insert(adminChannel);
    }

    @Override
    public void changeChannelStatus(ChangeChannelStatusDTO changeChannelStatusDTO) {
        AdminChannel adminChannel = new AdminChannel();
        BeanUtils.copyProperties(changeChannelStatusDTO, adminChannel);
        adminChannelMapper.updateById(adminChannel);
    }

    @Override
    @Transactional
    public void changeChannelOrder(ChangeChannelOrderDTO changeChannelOrderDTO) {
        List<ChannelOrderListDTO> channelOrderList = changeChannelOrderDTO.getChannelOrderList();
        List<AdminChannel> channelList = channelOrderList.stream()
                .map(dto -> new AdminChannel(dto.getId(), dto.getOrderNum())).collect(Collectors.toList());
        this.updateBatchById(channelList);
    }
}




