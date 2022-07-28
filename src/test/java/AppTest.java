import com.ll.exam.article.dto.ArticleDto;
import com.ll.exam.util.Ut;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
    @Test
    void 테스트_assertThat() {
        int rs = 10 + 20;
        assertThat(rs).isEqualTo(30);
    }

    @Test
    void 테스트_ObjectMapper() {
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
}
