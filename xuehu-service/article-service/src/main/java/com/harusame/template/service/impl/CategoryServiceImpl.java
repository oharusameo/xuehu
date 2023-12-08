package com.harusame.template.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harusame.template.domain.dto.AddCategoryDTO;
import com.harusame.template.domain.dto.DelCategoryDTO;
import com.harusame.template.domain.dto.ModifyCategoryDTO;
import com.harusame.template.domain.pojo.Category;
import com.harusame.template.domain.vo.CategoryVo;
import com.harusame.template.domain.vo.CategoryVoList;
import com.harusame.template.domain.vo.ImageVo;
import com.harusame.template.enums.CreateCategoryEnum;
import com.harusame.template.exception.BusinessException;
import com.harusame.template.service.CategoryService;
import com.harusame.template.mapper.CategoryMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ggzst
 * @description 针对表【t_category(分类表)】的数据库操作Service实现
 * @createDate 2023-12-06 16:41:20
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
        implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public Category addCategory(AddCategoryDTO addCategoryDTO) {
        Integer userId = StpUtil.getLoginIdAsInt();
//        if (categoryMapper.selectOne(new QueryWrapper<Category>()
//                .eq("category_name", addCategoryDTO.getCategoryName())
//                .eq("user_id", userId)) != null) {
//            throw new BusinessException("分类已存在,新增失败");
//        }
        if (lambdaQuery()
                .eq(Category::getCategoryName, addCategoryDTO.getCategoryName())
                .eq(Category::getUserId, userId).one() != null) {
            throw new BusinessException("分类名称已存在,新增失败");
        }

        Category category = new Category();
        Date date = new Date();
        category.setCategoryName(addCategoryDTO.getCategoryName());
        category.setUserId(userId);
        category.setCreateUser(userId);
        category.setUpdateUser(userId);
        category.setCreateTime(date);
        category.setUpdateTime(date);
        category.setCategoryType(CreateCategoryEnum.CREATE_BY_USER);
        categoryMapper.insert(category);
        return category;
    }

    @Override
    public void modifyCategory(ModifyCategoryDTO modifyCategoryDTO) {
        Integer userId = StpUtil.getLoginIdAsInt();
        String categoryName = modifyCategoryDTO.getCategoryName();
        Category category = lambdaQuery()
                .eq(Category::getUserId, userId)
                .eq(Category::getCategoryName, categoryName)
                .eq(Category::getCategoryType, CreateCategoryEnum.CREATE_BY_SYSTEM)//默认文件夹不允许修改
                .ne(Category::getId, modifyCategoryDTO.getCategoryId())//查询时需要排除自身
                .one();
        if (category != null) {
            throw new BusinessException("修改失败");
        }
        category = new Category();
        category.setId(modifyCategoryDTO.getCategoryId());
        category.setCategoryName(modifyCategoryDTO.getCategoryName());
        categoryMapper.updateById(category);
    }

    @Override
    @Transactional
    public void deleteCategory(DelCategoryDTO delCategoryDTO) {
        Integer userId = StpUtil.getLoginIdAsInt();
        Category category = lambdaQuery()
                .eq(Category::getId, delCategoryDTO.getCategoryId())
                .eq(Category::getUserId, userId)
                .select(Category::getId, Category::getImageIds, Category::getCategoryType).one();
        if (category == null) {
            throw new BusinessException("该分类不存在");
        }
        if (CreateCategoryEnum.CREATE_BY_SYSTEM == category.getCategoryType()) {
            throw new BusinessException("该分类不允许删除");
        }
        Category defaultCategory = lambdaQuery()
                .eq(Category::getUserId, userId)
                .eq(Category::getCategoryType, CreateCategoryEnum.CREATE_BY_SYSTEM)
                .select(Category::getImageIds, Category::getId).one();
        //移动分类下的图片至默认分类并去重
        List<Integer> mergeImgIds = Stream.concat(category.getImageIds().stream(), defaultCategory.getImageIds().stream())
                .distinct().collect(Collectors.toList());
        defaultCategory.setImageIds(mergeImgIds);
        defaultCategory.setUpdateTime(new Date());
        defaultCategory.setUpdateUser(userId);
        int result = categoryMapper.updateById(defaultCategory);
        if (result != 1) {
            throw new BusinessException("更新默认分类失败");
        }
        //删除分类
        categoryMapper.deleteById(category.getId());
    }

    @Override
    public CategoryVoList getCategoryList() {
        Integer userId = StpUtil.getLoginIdAsInt();
        List<Category> categoryList = lambdaQuery().eq(Category::getUserId, userId).list();
        if (categoryList.isEmpty()) {
            throw new BusinessException("该用户下没有分类");
        }
        AtomicReference<Integer> totalCount = new AtomicReference<>(0);
        List<CategoryVo> categoryVoList = categoryList.stream().map(category -> {
                    totalCount.updateAndGet(v -> v + category.getImageIds().size());
                    return new CategoryVo(
                            category.getId(),
                            category.getCategoryName(),
                            category.getCategoryType(),
                            category.getImageIds().size()
                    );
                }
        ).collect(Collectors.toList());

        return new CategoryVoList(totalCount.get(), categoryVoList);
    }

    @Override
    public List<ImageVo> getImageListByCategory(Long categoryId) {
        Integer userId = StpUtil.getLoginIdAsInt();
        List<ImageVo> imageVoList = categoryMapper.selectImageList(userId, categoryId);
        if (imageVoList.isEmpty()) {
            throw new BusinessException("该分类下没有图片");
        }
        return imageVoList;
    }


}




