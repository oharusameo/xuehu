package com.harusame.template.mq.consumer;

import com.harusame.template.domain.vo.ArticleVo;
import com.harusame.template.service.ArticleService;
import com.harusame.template.service.CheckContentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
@RocketMQMessageListener(topic = "article-dev", consumerGroup = "group-1")
public class ArticleConsumer implements RocketMQListener<Long> {

    @Resource
    private RocketMQTemplate rocketMQTemplate;
    @Resource
    private CheckContentService checkContentService;
    @Reference
    private ArticleService articleService;

    @Override
    public void onMessage(Long articleId) {
        ArticleVo articleVo = articleService.getArticleContentById(articleId);
        checkContentService.checkContent(articleVo);

    }
}
