package com.harusame.template.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArticleVo implements Serializable {
    private Long id;
    private String articleTitle;
    private String content;
}
