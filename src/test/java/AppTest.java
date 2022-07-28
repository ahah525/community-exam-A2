import com.fasterxml.jackson.core.type.TypeReference;
import com.ll.exam.article.dto.ArticleDto;
import com.ll.exam.util.Ut;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
    @Test
    void 테스트_assertThat() {
        int rs = 10 + 20;
        assertThat(rs).isEqualTo(30);
    }

    @Test
    void 테스트_ObjectMapper__ArticleDtoToJsonStr() {
        // given: ArticleDto 생성
        ArticleDto articleDto = new ArticleDto(1, "제목", "내용");
        // when: json 문자열로 변환
        String jsonStr = Ut.json.toStr(articleDto, null);
        // then: 변환된 json 문자열이 null이 아니고 제대로 변환되었는지 검증
        assertThat(jsonStr).isNotBlank();
        assertThat(jsonStr).isEqualTo("""
                {"id":1,"title":"제목","body":"내용"}
                """.trim());
    }

    @Test
    void 테스트_ObjectMapper__ArticleDtoListToJsonStr() {
        // given
        List<ArticleDto> articleDtos = new ArrayList<>();
        articleDtos.add(new ArticleDto(1, "제목1", "내용1"));
        articleDtos.add(new ArticleDto(2, "제목2", "내용2"));
        // when
        String jsonStr = Ut.json.toStr(articleDtos, "");
        // then
        assertThat(jsonStr).isEqualTo("""
                [{"id":1,"title":"제목1","body":"내용1"},{"id":2,"title":"제목2","body":"내용2"}]
                """.trim());
    }

    @Test
    void 테스트_ObjectMapper__ArticleDtoMapToJsonStr() {
        // given
        Map<String, ArticleDto> articleDtoMap = new HashMap<>();
        articleDtoMap.put("old", new ArticleDto(1, "제목1", "내용1"));
        articleDtoMap.put("new", new ArticleDto(2, "제목2", "내용2"));
        // when
        String jsonStr = Ut.json.toStr(articleDtoMap, "");
        // then
        assertThat(jsonStr).contains("""
                "old":{"id":1,"title":"제목1","body":"내용1"}
                """.trim());
        assertThat(jsonStr).contains("""
                "new":{"id":2,"title":"제목2","body":"내용2"}
                """.trim());
    }

    @Test
    void 테스트_ObjectMapper__JsonStrToObj() {
        // given: ArticleDto 생성
        ArticleDto articleDto = new ArticleDto(1, "제목", "내용");
        String jsonStr = Ut.json.toStr(articleDto, null);
        // when: Json -> Java 객체 변환
        ArticleDto articleDtoFromJson = Ut.json.toObj(jsonStr, ArticleDto.class, null);
        // then: ArticleDto가 제대로 변환되었는지 검증
        assertThat(articleDtoFromJson).isEqualTo(articleDto);
    }

    @Test
    void 테스트_ObjectMapper__JsonStrToArticleDtoList() {
        // given
        List<ArticleDto> articleDtos = new ArrayList<>();
        articleDtos.add(new ArticleDto(1, "제목1", "내용1"));
        articleDtos.add(new ArticleDto(2, "제목2", "내용2"));
        String jsonStr = Ut.json.toStr(articleDtos, "");
        // when
        List<ArticleDto> articleDtosFromJson = Ut.json.toObj(jsonStr, new TypeReference<>() {}, null);
        // then
        assertThat(articleDtosFromJson).isEqualTo(articleDtos);
    }

    @Test
    void ObjectMapper__jsonStrToArticleDtoMap() {
        Map<String, ArticleDto> articleDtoMap = new HashMap<>();
        articleDtoMap.put("old", new ArticleDto(1, "제목1", "내용1"));
        articleDtoMap.put("new", new ArticleDto(2, "제목2", "내용2"));
        String jsonStr = Ut.json.toStr(articleDtoMap, "");

        Map<String, ArticleDto> articleDtoMapFromJson = Ut.json.toMap(jsonStr, new TypeReference<>() {
        }, null);

        System.out.println(jsonStr);
    }
}
