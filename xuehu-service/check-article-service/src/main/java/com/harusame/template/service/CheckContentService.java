package com.harusame.template.service;

import com.harusame.template.domain.dto.GetArticleDTO;
import com.harusame.template.domain.vo.ArticleVo;

public interface CheckContentService {

    void checkContent(ArticleVo articleVo);

}
