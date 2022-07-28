package com.ll.exam.article.dto;

import lombok.*;

// @Data = @Getter + @Setter + @ToString
@Data
@AllArgsConstructor
public class ArticleDto {
    private long id;
    private String title;   // 제목
    private String body;    // 내용
}
