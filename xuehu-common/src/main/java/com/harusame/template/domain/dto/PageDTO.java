package com.harusame.template.domain.dto;

import lombok.Data;

import java.util.PrimitiveIterator;

@Data
public class PageDTO {
    private Integer pageIndex;
    private Integer pageSize;
}
