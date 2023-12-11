package com.harusame.template.service.impl;

import com.aliyun.imageaudit20191230.Client;
import com.aliyun.imageaudit20191230.models.*;
import com.aliyun.tea.TeaException;
import com.aliyun.teautil.Common;
import com.aliyun.teautil.models.RuntimeOptions;
import com.harusame.template.config.AliyunConfig;
import com.harusame.template.exception.BusinessException;
import com.harusame.template.service.CheckContentService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class CheckContentServiceImpl implements CheckContentService {
    @Resource
    private Client client;


    @Override
    public void checkContent(String html) {
        //检查文本内容
        checkText(html);
        //检查图片
        Document document = Jsoup.parse(html);
        document.select("img").forEach(img -> {
            String src = img.attr("src");
            log.info("src:{}", src);
            checkImage(src);
        });

    }

    public void checkImage(String imageUrl) {
        ScanImageRequest.ScanImageRequestTask task0 = new ScanImageRequest.ScanImageRequestTask()
                .setImageURL(imageUrl);
        ScanImageRequest scanImageRequest = new ScanImageRequest()
                .setScene(Collections.singletonList(
                        "porn"
                ))
                .setTask(Collections.singletonList(
                        task0
                ));
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
        List<ScanImageResponseBody.ScanImageResponseBodyDataResultsSubResults> subResults = body.getData().getResults().get(0).getSubResults();
        for (ScanImageResponseBody.ScanImageResponseBodyDataResultsSubResults subResult : subResults) {
            String suggestion = subResult.getSuggestion();
            String scene = subResult.getScene();
            String label = subResult.getLabel();
            log.info("scene:{},label:{},suggestion:{}", scene, label, suggestion);
        }
    }

    public void checkText(String html) {
        ScanTextRequest.ScanTextRequestLabels labels0 = new ScanTextRequest.ScanTextRequestLabels()
                .setLabel("abuse");
        ScanTextRequest.ScanTextRequestTasks tasks0 = new ScanTextRequest.ScanTextRequestTasks()
                .setContent(html);
        ScanTextRequest scanTextRequest = new ScanTextRequest()
                .setTasks(Collections.singletonList(
                        tasks0
                ))
                .setLabels(Collections.singletonList(
                        labels0
                ));

        ScanTextResponse scanTextResponse = null;
        try {
            // 复制代码运行请自行打印 API 的返回值
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
        List<ScanTextResponseBody.ScanTextResponseBodyDataElementsResults> results = scanTextResponse.getBody().getData().getElements().get(0).getResults();
        for (ScanTextResponseBody.ScanTextResponseBodyDataElementsResults result : results) {
            String suggestion = result.getSuggestion();
            String label = result.getLabel();
            Float rate = result.getRate();
            log.info("label:{},rate:{},suggestion:{}", label, rate, suggestion);
        }

    }
}
