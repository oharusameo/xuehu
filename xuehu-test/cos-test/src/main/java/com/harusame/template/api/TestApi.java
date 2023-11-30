package com.harusame.template.api;

import com.harusame.template.cos.CosProperties;
import com.harusame.template.cos.CosTemplate;
import com.harusame.template.domain.pojo.Result;
import com.qcloud.cos.model.COSObject;
import com.qcloud.cos.model.COSObjectInputStream;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.model.ObjectMetadata;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;

@Api(tags = "测试接口")
@RequestMapping("/test")
@RestController
public class TestApi {

    @Resource
    private CosTemplate cosTemplate;

    @ApiOperation(value = "上传文件", notes = "上传文件")
    @PostMapping("/upload")
    public Result upload() {
        File file = new File("f://22.png");
        try (FileInputStream inputStream = new FileInputStream(file)) {
            cosTemplate.upload("picture1", inputStream);
        } catch (IOException ioException) {
            return Result.error(ioException.getMessage());
        }
        return Result.success();


    }
}
