package com.harusame.template.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harusame.template.domain.pojo.Img;
import com.harusame.template.service.ImgService;
import com.harusame.template.mapper.ImgMapper;
import org.springframework.stereotype.Service;

/**
* @author ggzst
* @description 针对表【t_img(图片表)】的数据库操作Service实现
* @createDate 2023-12-06 16:41:20
*/
@Service
public class ImgServiceImpl extends ServiceImpl<ImgMapper, Img>
    implements ImgService{

}




