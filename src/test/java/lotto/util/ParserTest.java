package lotto.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    void 로또_번호_숫자_문자열을_리스트로_반환한다() {
        List<Integer> result = Parser.parseNumbers("1, 2, 3, 4, 5, 6");
        assertThat(result).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    void 로또_번호_숫자_외_입력_시_예외가_발생한다() {
        assertThatThrownBy(() -> Parser.parseNumbers("1, 2, 3, /, 4, 5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 숫자가 아닌 입력이 포함되어 있습니다.");
    }

    @Test
    void 구입_금액_문자열을_숫자로_제대로_파싱한다() {
        assertThat(Parser.parsePurchaseAmount("9")).isInstanceOf(Integer.class);
    }

    @Test
    void 구입_금액_숫자_외_입력_시_예외가_발생한다() {
        assertThatThrownBy(() -> Parser.parsePurchaseAmount("abcd"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액은 숫자만 입력 가능합니다.");
    }

    @Test
    void 보너스_번호_문자열을_숫자로_제대로_파싱한다() {
        assertThat(Parser.parseBonusNumber("9")).isInstanceOf(Integer.class);
    }

    @Test
    void 보너스_번호_숫자_외_입력_시_예외가_발생한다() {
        assertThatThrownBy(() -> Parser.parseBonusNumber("abcd"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 보너스 번호는 숫자만 입력 가능합니다.");
    }
}
