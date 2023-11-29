package com.harusame.template.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.harusame.template.domain.dto.AddChannelDTO;
import com.harusame.template.domain.dto.ChangeChannelOrderDTO;
import com.harusame.template.domain.dto.ChangeChannelStatusDTO;
import com.harusame.template.domain.dto.QueryChannelDTO;
import com.harusame.template.domain.pojo.AdminChannel;
import com.harusame.template.domain.pojo.PageResult;
import com.harusame.template.domain.pojo.Result;
import com.harusame.template.service.AdminChannelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Api(tags = "频道模块")
@RestController
@RequestMapping("/api/v1/adminChannel")
public class AdminChannelApi {
    @Resource
    private AdminChannelService adminChannelService;

    @PostMapping("/changeChannelStatus")
    @ApiOperation("修改频道状态接口")
    public Result changeChannelStatus(@ApiParam(name = "token", value = "身份认证令牌")
                                      @RequestHeader String token, @RequestBody @Valid ChangeChannelStatusDTO changeChannelStatusDTO) {
        adminChannelService.changeChannelStatus(changeChannelStatusDTO);
        return Result.success("修改频道状态成功");
    }

    @PostMapping("/changeChannelOrder")
    @ApiOperation("修改频道顺序接口")
    public Result changeChannelOrder(@ApiParam(name = "token", value = "身份认证令牌")
                                     @RequestHeader String token, @RequestBody @Valid ChangeChannelOrderDTO changeChannelOrderDTO) {
        adminChannelService.changeChannelOrder(changeChannelOrderDTO);
        return Result.success("修改频道顺序成功");

    }

    @PostMapping("/addChannel")
    @ApiOperation("新增频道接口")
    public Result addChannel(@ApiParam(name = "token", value = "身份认证令牌")
                             @RequestHeader String token, @RequestBody @Valid AddChannelDTO addChannelDTO) {
        adminChannelService.addChannel(addChannelDTO);
        return Result.success("新增频道成功");
    }

    @PostMapping("/list")
    @ApiOperation("查询频道列表")
    public PageResult<AdminChannel> list(@RequestBody QueryChannelDTO queryChannelDTO) {
        return adminChannelService.queryPage(queryChannelDTO);
    }


}
