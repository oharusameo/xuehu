package com.harusame.template.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryVoList {
    Integer imageTotalCount;
    List<CategoryVo> categoryVoList;
}
