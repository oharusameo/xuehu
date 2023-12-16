package com.harusame.template.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harusame.template.domain.dto.GetArticleDTO;
import com.harusame.template.domain.dto.PreReleaseArticleDTO;
import com.harusame.template.domain.pojo.Article;
import com.harusame.template.domain.vo.ArticleVo;
import com.harusame.template.enums.ArticleStatusEnum;
import com.harusame.template.exception.BusinessException;
import com.harusame.template.service.ArticleService;
import com.harusame.template.mapper.ArticleMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
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
@Slf4j
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
        implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private RocketMQTemplate rocketMQTemplate;

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
        rocketMQTemplate.asyncSend("article-dev", article.getId(), new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {

                log.info("审核信息发送成功");
            }
            @Override
            public void onException(Throwable e) {
                log.error("审核信息发送失败", e);
            }
        });

    }

/*    @Override
    public ArticleVo getArticleContentById(GetArticleDTO getArticleDTO) {
        Article article = lambdaQuery().select(Article::getId, Article::getArticleTitle, Article::getContent, Article::getUserId)
                .eq(Article::getId, getArticleDTO.getArticleId())
                .eq(Article::getArticleStatus, ArticleStatusEnum.PENDING)
                .one();
        if (article == null) {
            throw new BusinessException("文章不存在");
        }
        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article, articleVo);
        return articleVo;
    }*/
}




