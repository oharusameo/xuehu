package com.harusame.template.api;

import com.harusame.template.domain.vo.ArticleVo;
import com.harusame.template.service.ArticleService;
import com.harusame.template.domain.dto.GetArticleDTO;
import com.harusame.template.domain.pojo.Result;
import com.harusame.template.service.CheckContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@Api(tags = "文章内容审核模块")
@RequestMapping("/check-article-service/api/v1/checkArticleContent")
@RestController
public class CheckArticleContentApi {
    @Resource
    private CheckContentService checkContentService;

    @Reference
    private ArticleService articleService;

    @PostMapping("/checkArticleContent")
    @ApiOperation("审核文章内容接口")
    public Result checkArticleContent(@RequestBody @Valid Long articleId) {
        ArticleVo articleVo = articleService.getArticleContentById(articleId);
        checkContentService.checkContent(articleVo);
        return Result.success(articleVo);
    }


}
