package com.harusame.template.cos;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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

    public String getObjectUrl(String fileName) {
        URL objectUrl = cosClient.getObjectUrl(cosProperties.getBucket(), fileName);
        return objectUrl.toString();
    }

    public void deleteObject(String fileName) {
        DeleteObjectRequest request = new DeleteObjectRequest(cosProperties.getBucket(), fileName);
        cosClient.deleteObject(request);
    }

    public void deleteObjects(List<String> keys) {
        List<DeleteObjectsRequest.KeyVersion> delObjects = new ArrayList<>(keys.size());
        for (String key : keys) {
            delObjects.add(new DeleteObjectsRequest.KeyVersion(key));
        }
        DeleteObjectsRequest request = new DeleteObjectsRequest(cosProperties.getBucket());
        request.withKeys(delObjects);
        DeleteObjectsResult result = cosClient.deleteObjects(request);
        List<DeleteObjectsResult.DeletedObject> list = result.getDeletedObjects();
        log.info(list.toString());
    }


}
