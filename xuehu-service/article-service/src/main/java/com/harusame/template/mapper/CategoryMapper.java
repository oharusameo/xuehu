package com.harusame.template.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.harusame.template.domain.pojo.Category;
import com.harusame.template.domain.vo.ImageVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ggzst
 * @description 针对表【t_category(分类表)】的数据库操作Mapper
 * @createDate 2023-12-06 16:41:20
 * @Entity com.harusame.template.domain.pojo.Category
 */
public interface CategoryMapper extends BaseMapper<Category> {


    List<ImageVo> selectImageList(@Param("user_id") Integer userId, @Param("category_id") Long categoryId);

    Integer updateRelationShip(@Param("user_id") Integer userId, @Param("category_id") Long categoryId, @Param("image_id") Integer imgId);
}




