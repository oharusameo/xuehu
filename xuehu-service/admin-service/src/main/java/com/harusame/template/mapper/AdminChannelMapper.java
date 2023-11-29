package com.harusame.template.mapper;

import com.harusame.template.domain.pojo.AdminChannel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author ggzst
 * @description 针对表【admin_channel(频道信息表)】的数据库操作Mapper
 * @createDate 2023-11-28 18:52:16
 * @Entity com.harusame.template.domain.pojo.AdminChannel
 */
public interface AdminChannelMapper extends BaseMapper<AdminChannel> {

    @Select("select max(order_num) order_num,(select name from admin_channel WHERE (name = #{name})) name  from admin_channel")
    AdminChannel selectChannelByName(String name);
}




