package com.harusame.template.api;

import com.harusame.template.domain.dto.DelImageDTO;
import com.harusame.template.domain.pojo.Result;
import com.harusame.template.domain.vo.ImageVo;
import com.harusame.template.service.ImgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;

@RequestMapping("/article-service/api/v1/image")
@RestController
@Api(tags = "图片模块")
public class ImageApi {
    @Resource
    private ImgService imgService;

    @PostMapping("/uploadImage")
    @ApiOperation("上传图片接口")
    public Result uploadImage(@ApiParam(name = "token", value = "身份认证令牌")
                              @RequestHeader String token, @RequestPart("img") MultipartFile file,
                              @RequestParam Long categoryId) {
        ImageVo imageVo = imgService.uploadImage(file, categoryId);
        return Result.success(imageVo);
    }


    @DeleteMapping("/deleteImage")
    @ApiOperation("删除图片接口")
    public Result deleteImage(@ApiParam(name = "token", value = "身份认证令牌")
                              @RequestHeader String token, @RequestBody @Valid DelImageDTO delImageDTO) {
        imgService.deleteImage(delImageDTO);
        return Result.successMsg("删除图片成功");
    }


}
