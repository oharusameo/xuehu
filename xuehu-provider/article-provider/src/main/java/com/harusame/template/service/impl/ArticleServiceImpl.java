package com.harusame.template.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harusame.template.domain.pojo.Article;
import com.harusame.template.domain.vo.ArticleVo;
import com.harusame.template.enums.ArticleStatusEnum;
import com.harusame.template.exception.BusinessException;
import com.harusame.template.mapper.ArticleMapper;
import com.harusame.template.service.ArticleService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Override
    public ArticleVo getArticleContentById(Long articleId) {
        Article article = lambdaQuery().select(Article::getId, Article::getArticleTitle, Article::getContent)
                .eq(Article::getId, articleId)
                .eq(Article::getArticleStatus, ArticleStatusEnum.PENDING)
                .one();
        if (article == null) {
            throw new BusinessException("文章不存在");
        }
        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article, articleVo);
        return articleVo;
    }

    @Override
    public Boolean updateArticleStatus(Long articleId) {
        return lambdaUpdate().set(Article::getArticleStatus, ArticleStatusEnum.APPROVED)
                .eq(Article::getId, articleId)
                .update();
    }
}
