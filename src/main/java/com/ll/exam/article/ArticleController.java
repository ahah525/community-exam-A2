package com.ll.exam.article;

import com.ll.exam.Rq;
import com.ll.exam.article.dto.ArticleDto;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.util.List;

public class ArticleController {
    private ArticleService articleService;

    public ArticleController() {
        articleService = new ArticleService();
    }

    // 게시물 조회
    public void showList(Rq rq) throws IOException, ServletException {
        List<ArticleDto> articleDtos = articleService.findAll();
        // jsp에서 사용할 수 있도록 request에 게시물 리스트 담기
        rq.setAttr("articles", articleDtos);

        rq.view("usr/article/list");
    }

    // 게시물 작성폼
    public void showWrite(Rq rq) throws IOException, ServletException {
        rq.view("usr/article/write");
    }

    // 게시물 등록
    public void doWrite(Rq rq) throws ServletException, IOException {
        // 요청에서 온 값 읽어서 response
        String title = rq.getParam("title", "");
        String body = rq.getParam("body", "");

        if (title.length() == 0) {
            rq.historyBack("제목을 입력해주세요.");
            return;
        }

        if (body.length() == 0) {
            rq.historyBack("내용을 입력해주세요.");
            return;
        }

        // 생성된 게시물의 id
        long id = articleService.write(title, body);
        // 게시물 생성 문구 출력 후 게시물 리스트 페이지로 이동
        rq.replace("/usr/article/list/free", "%d번 게시물이 생성되었습니다.".formatted(id));
    }

    // 게시물 상세화면 조회
    public void showDetail(Rq rq) throws ServletException, IOException {
        // free/1
        long id = rq.getLongPathValueByIndex(1, 0);

        // 게시물 번호가 입력되지 않았을 경우 예외처리
        if (id == 0) {
            rq.historyBack("번호를 입력해주세요.");
            return;
        }

        ArticleDto articleDto = articleService.findById(id);
        // 해당 id에 대한 게시글이 없을 경우 예외처리
        if (articleDto == null) {
            rq.historyBack("해당 게시글은 존재하지 않습니다.");
            return;
        }

        rq.setAttr("article", articleDto);
        rq.view("usr/article/detail");
    }

    // 게시물 삭제
    public void doDelete(Rq rq) throws IOException {
        // free/1
        long id = rq.getLongPathValueByIndex(1, 0);

        if (id == 0) {
            rq.historyBack("번호를 입력해주세요.");
            return ;
        }

        ArticleDto articleDto = articleService.findById(id);
        // 해당 id에 대한 게시글 없을 경우 예외처리
        if (articleDto == null) {
            rq.historyBack("해당 게시글은 존재하지 않습니다.");
            return;
        }
        articleService.delete(id);

        // 게시글 삭제 문구 출력 후 리스트 페이지 이동
        rq.replace("/usr/article/list/free", "%d번 게시물이 삭제되었습니다.".formatted(id));
    }

    // 게시물 수정폼
    public void showModifyForm(Rq rq) throws ServletException, IOException {
        //free/1
        long id = rq.getLongPathValueByIndex(1, 0);

        if (id == 0) {
            rq.historyBack("번호를 입력해주세요.");
            return ;
        }

        ArticleDto articleDto = articleService.findById(id);

        // 해당 id에 대한 게시글 없을 경우 예외처리
        if (articleDto == null) {
            rq.historyBack("해당 게시글은 존재하지 않습니다.");
            return;
        }

        rq.setAttr("article", articleDto);
        rq.view("usr/article/modify");
    }

    public void doModify(Rq rq) throws IOException {
        //free/1
        long id = rq.getLongPathValueByIndex(1, 0);

        if (id == 0) {
            rq.historyBack("번호를 입력해주세요.");
            return ;
        }

        ArticleDto articleDto = articleService.findById(id);

        // 해당 id에 대한 게시글 없을 경우 예외처리
        if (articleDto == null) {
            rq.historyBack("해당 게시글은 존재하지 않습니다.");
            return;
        }

        // 요청에서 온 값 읽어서 response
        String title = rq.getParam("title", "");
        String body = rq.getParam("body", "");
        articleService.modify(id, title, body);

        // 게시글 수정 문구 출력 후 리스트 페이지 이동
        rq.replace("/usr/article/list/free", "%d번 게시물이 수정되었습니다.".formatted(id));
    }
}
