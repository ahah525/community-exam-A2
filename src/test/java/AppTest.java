import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
    @Test
    void assertThat_테스트() {
        int rs = 10 + 20;
        assertThat(rs).isEqualTo(30);
    }
}
