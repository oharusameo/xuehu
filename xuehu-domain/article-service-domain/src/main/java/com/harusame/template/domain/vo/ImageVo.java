package com.harusame.template.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageVo {
    private Long id;
    private String imgName;
    private String bigImgFullPath;
    private String smallImgFullPath;

}
