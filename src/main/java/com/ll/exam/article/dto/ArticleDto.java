package com.ll.exam.article.dto;

import lombok.*;

// @Data = @Getter + @Setter + @ToString
@Data
@AllArgsConstructor
@NoArgsConstructor  // 매개변수 없는 생성자
public class ArticleDto {
    private long id;
    private String title;   // 제목
    private String body;    // 내용
}
