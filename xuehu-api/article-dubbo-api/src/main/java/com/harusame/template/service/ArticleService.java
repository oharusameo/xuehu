package com.harusame.template.service;

import com.harusame.template.domain.vo.ArticleVo;


public interface ArticleService {
    ArticleVo getArticleContentById(Long articleId);

    Boolean updateArticleStatus(Long articleId);
}
