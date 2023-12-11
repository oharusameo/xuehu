package com.harusame.template.api;

import com.harusame.template.domain.dto.PreReleaseArticleDTO;
import com.harusame.template.domain.pojo.Result;
import com.harusame.template.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Api(tags = "文章模块")
@RequestMapping("/article-service/api/v1/article")
@RestController
public class ArticleApi {
    @Resource
    private ArticleService articleService;

    @PostMapping("/preReleaseArticle")
    @ApiOperation("预发布文章接口")
    public Result preReleaseArticle(@ApiParam(name = "token", value = "身份认证令牌")
                                    @RequestHeader String token, @RequestBody @Valid PreReleaseArticleDTO preReleaseArticleDTO) {
        articleService.preReleaseArticle(preReleaseArticleDTO);
        return Result.successMsg("文章预发布成功");
    }

}
