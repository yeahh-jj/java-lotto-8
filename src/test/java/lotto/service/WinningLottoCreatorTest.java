package lotto.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.Test;

public class WinningLottoCreatorTest {
    @Test
    void 로또_번호가_6개_중복없이_1에서_45_사이의_숫자로_생성되는지_확인() {
        List<Integer> numbers = WinningLottoCreator.createWinningNumbers();

        assertThat(numbers).hasSize(6);
        assertThat(new HashSet<>(numbers)).hasSize(6);
        assertThat(numbers).allMatch(n -> n >= 1 && n <= 45);
    }


    @Test
    void 보너스_번호가_당첨_번호와_중복되지_않고_1에서_45_사이의_숫자인지_확인() {
        List<Integer> numbers = WinningLottoCreator.createWinningNumbers();
        int bonus = WinningLottoCreator.createBonusNumber(numbers);

        assertThat(bonus).isBetween(1, 45);
        assertThat(numbers).doesNotContain(bonus);
    }
}
