package com.harusame.template.domain.dto;

import lombok.Data;

@Data
public class PageDTO {

    private Integer pageIndex;
    private Integer pageSize;


    public void checkParam() {
        if (this.getPageIndex() == null || this.getPageIndex() < 0) {
            this.setPageIndex(1);
        }
        if (this.getPageSize() == null || this.getPageSize() < 0) {
            this.setPageSize(5);
        }
    }
}
