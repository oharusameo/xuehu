package com.harusame.template.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harusame.template.common.utils.FileUtils;
import com.harusame.template.cos.CosTemplate;
import com.harusame.template.domain.dto.DelImageDTO;
import com.harusame.template.domain.pojo.Category;
import com.harusame.template.domain.pojo.Img;
import com.harusame.template.domain.vo.ImageVo;
import com.harusame.template.exception.BusinessException;
import com.harusame.template.mapper.CategoryMapper;
import com.harusame.template.service.CategoryService;
import com.harusame.template.service.ImgService;
import com.harusame.template.mapper.ImgMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ggzst
 * @description 针对表【t_img(图片表)】的数据库操作Service实现
 * @createDate 2023-12-06 16:41:20
 */
@Service
public class ImgServiceImpl extends ServiceImpl<ImgMapper, Img>
        implements ImgService {
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private ImgMapper imgMapper;
    @Resource
    private CosTemplate cosTemplate;

    @Override
    @Transactional
    public ImageVo uploadImage(MultipartFile file, Long categoryId) {
        Integer userId = StpUtil.getLoginIdAsInt();
        //1.对文件进行合法性校验
        ByteArrayInputStream byteArrayInputStream;
        try {
            byteArrayInputStream = FileUtils.convertToByteArrayInputStream(file.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FileUtils.checkImageType(byteArrayInputStream);
        //2.计算图片的哈希值
        byteArrayInputStream.reset();//重置流
        String md5Hash = FileUtils.getMD5Hash(byteArrayInputStream);
        //3.直接查询图片表中是否存在相同哈希值的记录
        Img img = lambdaQuery().eq(Img::getImgHash, md5Hash).one();
        //3.1已经存在同样的图片，则不上传
        if (img != null) {
            Integer updateCount = categoryMapper.updateRelationShip(userId, categoryId, img.getId());
            if (updateCount == 0) {
                throw new BusinessException("当前分类已经存在该图片");
            }
            //图片的引用数量+1
            img.setRefCount(img.getRefCount() + 1);
            updateById(img);
            ImageVo imageVo = new ImageVo();
            BeanUtils.copyProperties(img, imageVo);
            return imageVo;
        }


        String objectName = UUID.randomUUID() + file.getOriginalFilename();
        objectName = objectName.replace("-", "");
        //3.2.如果没有，则上传文件到COS
        try {
            cosTemplate.upload(objectName, file.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String objectUrl = cosTemplate.getObjectUrl(objectName);
        img = new Img();
        img.setImgHash(md5Hash);
        img.setImgName(file.getOriginalFilename());
        img.setCreateUser(userId);
        img.setUserId(userId);
        img.setCreateTime(new Date());
        img.setRefCount(1);
        img.setBigImgFullPath(objectUrl);
        img.setBigImgSubPath(objectUrl);
        img.setSmallImgFullPath(objectName + "?imageMogr2/thumbnail/!50p");
        img.setSmallImgSubPath(objectUrl + "?imageMogr2/thumbnail/!50p");
        imgMapper.insert(img);
        ImageVo imageVo = new ImageVo();
        BeanUtils.copyProperties(img, imageVo);
        categoryMapper.updateRelationShip(userId, categoryId, img.getId());
        return imageVo;
    }

    @Override
    @Transactional
    public void deleteImage(DelImageDTO delImageDTO) {
        Integer userId = StpUtil.getLoginIdAsInt();
        List<Integer> delImageIds = delImageDTO.getDelImageIds();
        Category category = categoryMapper.selectOne(new LambdaQueryWrapper<Category>()
                .eq(Category::getId, delImageDTO.getCategoryId())
                .eq(Category::getUserId, userId)
        );
        if (category.getImageIds().isEmpty()) {
            throw new BusinessException("该分类下不存在图片");
        }
        List<Integer> imageIds = category.getImageIds();
        //过滤出分类下不需要被删除的图片Id
        List<Integer> remainImageIds =
                imageIds.stream().filter(imgId -> !delImageIds.contains(imgId)).collect(Collectors.toList());

        category.setImageIds(remainImageIds);
        categoryMapper.updateById(category);

        List<Img> delList = lambdaQuery().in(Img::getId, delImageIds).list();
        //筛选出引用数量=1的图片
        List<Img> delImageList = delList.stream().filter(item -> item.getRefCount() == 1).collect(Collectors.toList());
        //引用数量>1的图片则进行count-1
        for (Img img : delList) {
            img.setRefCount(img.getRefCount() - 1);
        }
        this.updateBatchById(delList);
        //对引用数量=1的图片进行删除
        imgMapper.deleteBatchIds(delImageList);

        List<String> list = delImageList.stream().map(Img::getSmallImgFullPath).collect(Collectors.toList());
        cosTemplate.deleteObjects(list);


    }


}




