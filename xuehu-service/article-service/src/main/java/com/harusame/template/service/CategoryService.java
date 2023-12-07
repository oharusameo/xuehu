package com.harusame.template.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.harusame.template.domain.dto.AddCategoryDTO;
import com.harusame.template.domain.dto.DelCategoryDTO;
import com.harusame.template.domain.dto.ModifyCategoryDTO;
import com.harusame.template.domain.pojo.Category;
import com.harusame.template.domain.vo.CategoryVo;
import com.harusame.template.domain.vo.CategoryVoList;

import java.util.List;

/**
* @author ggzst
* @description 针对表【t_category(分类表)】的数据库操作Service
* @createDate 2023-12-06 16:41:20
*/
public interface CategoryService extends IService<Category> {

    Category addCategory(AddCategoryDTO addCategoryDTO);

    void modifyCategory(ModifyCategoryDTO modifyCategoryDTO);

    void deleteCategory(DelCategoryDTO delCategoryDTO);

    CategoryVoList getCategoryList();

}
