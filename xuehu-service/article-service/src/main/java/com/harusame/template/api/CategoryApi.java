package com.harusame.template.api;

import com.harusame.template.domain.dto.AddCategoryDTO;
import com.harusame.template.domain.dto.DelCategoryDTO;
import com.harusame.template.domain.dto.ModifyCategoryDTO;
import com.harusame.template.domain.pojo.Category;
import com.harusame.template.domain.pojo.Result;
import com.harusame.template.domain.vo.CategoryVo;
import com.harusame.template.domain.vo.CategoryVoList;
import com.harusame.template.domain.vo.ImageVo;
import com.harusame.template.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Api(tags = "分类模块")
@RequestMapping("/article-service/api/v1/category")
@RestController
public class CategoryApi {
    @Resource
    private CategoryService categoryService;

    @GetMapping("/list")
    @ApiOperation("获取分类列表接口")
    public Result listCategory(@ApiParam(name = "token", value = "身份认证令牌")
                               @RequestHeader String token) {
        CategoryVoList categoryVoList = categoryService.getCategoryList();
        return Result.success(categoryVoList);
    }

    @GetMapping("/getImgListById")
    @ApiOperation("获取分类下的图片列表接口")
    public Result getImgListById(@ApiParam(name = "token", value = "身份认证令牌")
                                 @RequestHeader String token, @RequestParam @NotNull @Valid Long categoryId) {
        List<ImageVo> imageVoList = categoryService.getImageListByCategory(categoryId);
        return Result.success(imageVoList);
    }

    @PostMapping("/addCategory")
    @ApiOperation("新增分类接口")
    public Result addCategory(@ApiParam(name = "token", value = "身份认证令牌")
                              @RequestHeader String token, @Valid @RequestBody AddCategoryDTO addCategoryDTO) {
        Category category = categoryService.addCategory(addCategoryDTO);
        return Result.success(category);
    }

    @PutMapping("/modifyCategory")
    @ApiOperation("修改分类接口")
    public Result modifyCategory(@ApiParam(name = "token", value = "身份认证令牌")
                                 @RequestHeader String token, @RequestBody @Valid ModifyCategoryDTO modifyCategoryDTO) {
        categoryService.modifyCategory(modifyCategoryDTO);
        return Result.successMsg("修改成功");
    }

    @DeleteMapping("/deleteCategory")
    @ApiOperation("删除分类接口")
    public Result deleteCategory(@ApiParam(name = "token", value = "身份认证令牌")
                                 @RequestHeader String token, @RequestBody @Valid DelCategoryDTO delCategoryDTO) {
        categoryService.deleteCategory(delCategoryDTO);
        return Result.successMsg("删除成功");
    }

}
