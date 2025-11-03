package lotto.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

public class ValidatorTest {
    @Test
    void 구입_금액이_1000원_단위가_아니면_예외가_발생한다() {
        assertThatThrownBy(() -> Validator.validatePurchaseAmount(11500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액은 1000원 단위로만 입력할 수 있습니다.");
    }

    @Test
    void 구입_금액이_1000원_미만이면_예외가_발생한다() {
        assertThatThrownBy(() -> Validator.validatePurchaseAmount(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액은 1000원 이상 입력해야 합니다.");
    }

    @Test
    void 보너스_번호가_범위를_벗어나면_예외가_발생한다() {
        assertThatThrownBy(() -> Validator.validateBonusNumber(List.of(1, 2, 3, 4, 5, 6), 50))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 보너스 번호는 1~45 사이의 숫자만 입력 가능합니다.");
    }

    @Test
    void 보너스_번호가_당첨_번호와_중복이면_예외가_발생한다() {
        assertThatThrownBy(() -> Validator.validateBonusNumber(List.of(1, 2, 3, 4, 5, 6), 4))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호에 포함된 번호는 입력할 수 없습니다.");
    }
}
