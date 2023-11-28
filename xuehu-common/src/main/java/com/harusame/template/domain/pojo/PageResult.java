package com.harusame.template.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> extends Result {
    private Integer pageIndex;
    private Integer pageSize;
    private Long totalCount;
    private List<T> records;

}
