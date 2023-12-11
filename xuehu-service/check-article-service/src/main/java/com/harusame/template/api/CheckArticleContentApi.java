package com.harusame.template.api;

import com.harusame.template.domain.pojo.Result;
import com.harusame.template.service.CheckContentService;
import com.harusame.template.service.impl.CheckContentServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Api(tags = "检查文章内容模块")
@RequestMapping("/check-article-service/api/v1/check-article-content")
@RestController
public class CheckArticleContentApi {
    @Resource
    private CheckContentService checkContentService;
    @PostMapping("/checkArticleContent")
    @ApiOperation("检查文章内容接口")
    public Result checkArticleContent(@RequestBody @NotBlank @Valid @ApiParam("html") String html) {
        checkContentService.checkContent(html);

        return Result.success();
    }


}
