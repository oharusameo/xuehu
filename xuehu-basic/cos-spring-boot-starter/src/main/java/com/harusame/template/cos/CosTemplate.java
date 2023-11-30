package com.harusame.template.cos;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

@Data
@Slf4j
public class CosTemplate {

    private COSClient cosClient;

    @Resource
    private CosProperties cosProperties;

    public CosTemplate(COSClient cosClient) {
        this.cosClient = cosClient;
    }

    public void upload(String fileName, InputStream inputStream) {
        ObjectMetadata metadata = new ObjectMetadata();
        try {
            metadata.setContentLength(inputStream.available());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PutObjectRequest request = new PutObjectRequest(cosProperties.getBucket(), fileName, inputStream, metadata);
        PutObjectResult putObjectResult = cosClient.putObject(request);
        log.info(putObjectResult.getRequestId());
    }
}
