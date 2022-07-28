package com.ll.exam.article;

import com.ll.exam.article.dto.ArticleDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ArticleRepository {
    private static List<ArticleDto> articles;
    private static long lastId;

    // static 생성자로 초기화해야 함
    static {
        articles = new ArrayList<>();
        lastId = 0;

        makeTestData();
    }

    // 테스트 데이터 세팅(자동으로 값 입력해놓기 위해)
    private static void makeTestData() {
        // 10개 게시글 저장
        IntStream.rangeClosed(1, 10).forEach(id -> {
            String title = "제목%d".formatted(id);
            String body = "내용%d".formatted(id);
            write(title, body);
        });
    }

    // 등록된 게시물의 id 반환
    public static long write(String title, String body) {
        // 리스트에 등록
        ArticleDto articleDto = new ArticleDto(++lastId, title, body);
        articles.add(articleDto);

        return lastId;
    }

    public static List<ArticleDto> findAll() {
        return articles;
    }

    public static ArticleDto findById(long id) {
        for (ArticleDto articleDto : articles) {
            if(articleDto.getId() == id)
                return articleDto;
        }
        return null;
    }

    public void delete(long id) {
        ArticleDto articleDto = findById(id);
        articles.remove(articleDto);
    }

    public void modify(long id, String title, String body) {
        ArticleDto articleDto = findById(id);
        articleDto.setTitle(title);
        articleDto.setBody(body);
    }
}
