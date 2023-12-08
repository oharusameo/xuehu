package com.harusame.template.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.harusame.template.domain.dto.DelImageDTO;
import com.harusame.template.domain.pojo.Img;
import com.harusame.template.domain.vo.ImageVo;
import org.springframework.web.multipart.MultipartFile;

/**
* @author ggzst
* @description 针对表【t_img(图片表)】的数据库操作Service
* @createDate 2023-12-06 16:41:20
*/
public interface ImgService extends IService<Img> {

    ImageVo uploadImage(MultipartFile file,Long categoryId);

    void deleteImage(DelImageDTO delImageDTO);

}
