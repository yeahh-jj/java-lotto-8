package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class WinningNumbersTest {
    @Test
    void 여섯_개_번호가_일치하면_1등이다() {
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, 7);

        Lotto myLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        Rank rank = winningNumbers.rankOf(myLotto);

        assertThat(rank).isEqualTo(Rank.FIRST);
    }

    @Test
    void 다섯_개_번호와_보너스_번호가_일치하면_2등이다() {
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, 7);

        Lotto myLotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));

        Rank rank = winningNumbers.rankOf(myLotto);

        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @Test
    void 다섯_개_번호가_일치하면_3등이다() {
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, 7);

        Lotto myLotto = new Lotto(List.of(1, 2, 3, 4, 5, 10));

        Rank rank = winningNumbers.rankOf(myLotto);

        assertThat(rank).isEqualTo(Rank.THIRD);
    }

    @Test
    void 네_개_번호가_일치하면_4등이다() {
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, 7);

        Lotto myLotto = new Lotto(List.of(1, 2, 3, 4, 10, 11));

        Rank rank = winningNumbers.rankOf(myLotto);

        assertThat(rank).isEqualTo(Rank.FOURTH);
    }

    @Test
    void 세_개_번호가_일치하면_5등이다() {
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, 7);

        Lotto myLotto = new Lotto(List.of(1, 2, 3, 10, 11, 12));

        Rank rank = winningNumbers.rankOf(myLotto);

        assertThat(rank).isEqualTo(Rank.FIFTH);
    }

    @Test
    void 두_개_이하_번호가_일치하면_낙첨이다() {
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, 7);

        Lotto myLotto = new Lotto(List.of(1, 2, 10, 11, 12, 13));

        Rank rank = winningNumbers.rankOf(myLotto);

        assertThat(rank).isEqualTo(Rank.NONE);
    }
}
