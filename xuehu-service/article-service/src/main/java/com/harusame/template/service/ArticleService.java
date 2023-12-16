package com.harusame.template.service;

import com.harusame.template.domain.dto.GetArticleDTO;
import com.harusame.template.domain.dto.PreReleaseArticleDTO;
import com.harusame.template.domain.pojo.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.harusame.template.domain.vo.ArticleVo;

/**
 * @author ggzst
 * @description 针对表【t_article】的数据库操作Service
 * @createDate 2023-12-11 11:47:23
 */
public interface ArticleService extends IService<Article> {

    void preReleaseArticle(PreReleaseArticleDTO preReleaseArticleDTO);

//    ArticleVo getArticleContentById(GetArticleDTO getArticleDTO);
}
