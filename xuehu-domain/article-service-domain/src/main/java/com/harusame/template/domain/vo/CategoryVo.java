package com.harusame.template.domain.vo;

import com.harusame.template.enums.CreateCategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryVo {
    private Long id;
    private String categoryName;
    private CreateCategoryEnum categoryType;
    private Integer imageCount;
}
