package lotto.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.Test;

public class LottoNumberGeneratorTest {
    @Test
    void 로또_번호가_6개_중복없이_1에서_45_사이의_숫자로_생성되는지_확인() {
        List<Integer> numbers = LottoNumberGenerator.createWinningNumbers();

        assertThat(numbers).hasSize(6);
        assertThat(new HashSet<>(numbers)).hasSize(6);
        assertThat(numbers).allMatch(n -> n >= 1 && n <= 45);
    }
}
