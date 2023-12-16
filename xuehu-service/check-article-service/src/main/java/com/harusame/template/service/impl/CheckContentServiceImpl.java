package com.harusame.template.service.impl;

import com.aliyun.imageaudit20191230.Client;
import com.aliyun.imageaudit20191230.models.*;
import com.aliyun.tea.TeaException;
import com.aliyun.teautil.Common;
import com.aliyun.teautil.models.RuntimeOptions;
import com.harusame.template.service.ArticleService;
import com.harusame.template.domain.dto.GetArticleDTO;
import com.harusame.template.domain.vo.ArticleVo;
import com.harusame.template.exception.BusinessException;
import com.harusame.template.service.CheckContentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CheckContentServiceImpl implements CheckContentService {
    @Resource
    private Client client;
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Reference
    private ArticleService articleService;

    @Override
    public void checkContent(ArticleVo articleVo) {
        //检查文本内容
        Integer unPassCount = checkText(articleVo.getContent());
        //检查图片
        Document document = Jsoup.parse(articleVo.getContent());
        List<String> imgUrllist = new ArrayList<>();
        document.select("img").forEach(img -> {
            String src = img.attr("src");
            log.info("src:{}", src);
            imgUrllist.add(src);
        });
        if (!imgUrllist.isEmpty()) {
            unPassCount += checkImage(imgUrllist);
        }
        if (unPassCount > 0) {
//            throw new BusinessException("文章内容审核未通过");
            log.error("文章id:{}审核未通过", articleVo.getId());
        } else {
            articleService.updateArticleStatus(articleVo.getId());
            log.info("文章id:{}审核通过", articleVo.getId());
        }


    }

    public Integer checkImage(List<String> imageUrls) {
        List<ScanImageRequest.ScanImageRequestTask> imgUrlList = imageUrls.stream()
                .map(url -> new ScanImageRequest.ScanImageRequestTask().setImageURL(url)).collect(Collectors.toList());
        ScanImageRequest scanImageRequest = new ScanImageRequest()
                .setScene(Collections.singletonList(
                        "porn"
                ))
                .setTask(imgUrlList);
        ScanImageResponse scanImageResponse = null;
        try {
            scanImageResponse = client.scanImageWithOptions(scanImageRequest, new RuntimeOptions());
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 错误 message
            log.error(error.getMessage());
            // 诊断地址
            log.error("Recommend:{}", error.getData().get("Recommend"));
            Common.assertAsString(error.message);
        }
        if (scanImageResponse == null) {
            throw new BusinessException("响应错误");
        }
        ScanImageResponseBody body = scanImageResponse.getBody();
        List<ScanImageResponseBody.ScanImageResponseBodyDataResults> results = body.getData().getResults();
        int unPassCount = 0;
        for (ScanImageResponseBody.ScanImageResponseBodyDataResults result : results) {
            List<ScanImageResponseBody.ScanImageResponseBodyDataResultsSubResults> subResults = result.getSubResults();
            for (ScanImageResponseBody.ScanImageResponseBodyDataResultsSubResults subResult : subResults) {
                Float rate = subResult.getRate();
                String suggestion = subResult.getSuggestion();
                String scene = subResult.getScene();
                String label = subResult.getLabel();
                log.info("scene:{},label:{},suggestion:{},rate:{}", scene, label, suggestion, rate);
                unPassCount += count(suggestion);
            }
        }
        return unPassCount;
    }

    public Integer checkText(String html) {
        List<ScanTextRequest.ScanTextRequestLabels> labelList = new ArrayList<>();
        labelList.add(new ScanTextRequest.ScanTextRequestLabels()
                .setLabel("porn"));
        labelList.add(new ScanTextRequest.ScanTextRequestLabels()
                .setLabel("abuse"));
        ScanTextRequest scanTextRequest = new ScanTextRequest()
                .setTasks(Collections.singletonList(new ScanTextRequest.ScanTextRequestTasks()
                        .setContent(html)
                ))
                .setLabels(labelList);

        ScanTextResponse scanTextResponse = null;
        try {
            scanTextResponse = client.scanTextWithOptions(scanTextRequest, new RuntimeOptions());
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 错误 message
            log.error(error.getMessage());
            // 诊断地址
            log.error("Recommend:{}", error.getData().get("Recommend"));
            Common.assertAsString(error.message);
        }
        if (scanTextResponse == null) {
            throw new BusinessException("响应错误");
        }
        Integer unPassCount = 0;
        List<ScanTextResponseBody.ScanTextResponseBodyDataElementsResults> results = scanTextResponse.getBody().getData().getElements().get(0).getResults();
        for (ScanTextResponseBody.ScanTextResponseBodyDataElementsResults result : results) {
            String suggestion = result.getSuggestion();
            String label = result.getLabel();
            Float rate = result.getRate();
            log.info("label:{},rate:{},suggestion:{}", label, rate, suggestion);
            unPassCount += count(suggestion);
        }
        return unPassCount;
    }

    private Integer count(String suggestion) {
        int unPassCount = 0;
        switch (suggestion) {
            case "pass": {
                break;
            }
            case "review": {
            }
            case "block": {
                unPassCount++;
                break;
            }
        }
        return unPassCount;
    }

}
