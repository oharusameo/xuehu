package com.harusame.template.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harusame.template.domain.dto.PreReleaseArticleDTO;
import com.harusame.template.domain.pojo.Article;
import com.harusame.template.service.ArticleService;
import com.harusame.template.mapper.ArticleMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author ggzst
 * @description 针对表【t_article】的数据库操作Service实现
 * @createDate 2023-12-11 11:47:23
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
        implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;

    @Override
    public void preReleaseArticle(PreReleaseArticleDTO preReleaseArticleDTO) {
        Integer userId = StpUtil.getLoginIdAsInt();
        Article article = new Article();
        BeanUtils.copyProperties(preReleaseArticleDTO, article);
        article.setUserId(userId);
        Date date = new Date();
        article.setCreateTime(date);
        article.setUpdateTime(date);
        articleMapper.insert(article);
    }
}




