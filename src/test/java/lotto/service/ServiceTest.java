package lotto.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.WinningNumbers;
import org.junit.jupiter.api.Test;

public class ServiceTest {
    @Test
    void 구입_금액에_따라_로또_개수가_발행되는지_확인() {
        Service service = new Service();
        List<Lotto> lottos = service.purchaseLottos(8000);

        assertThat(lottos).hasSize(8);
    }

    @Test
    void 당첨_번호와_비교해서_등수가_맞게_집계되는지_확인() {
        Service service = new Service();
        WinningNumbers winningNumbers = new WinningNumbers(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7
        );

        List<Lotto> lottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 8)),
                new Lotto(List.of(1, 2, 3, 4, 8, 9)),
                new Lotto(List.of(11, 12, 13, 15, 16, 17))
        );

        Map<Rank, Long> result = service.calculateRanks(lottos, winningNumbers);

        assertThat(result.get(Rank.FIRST)).isEqualTo(1);
        assertThat(result.get(Rank.THIRD)).isEqualTo(1);
        assertThat(result.get(Rank.FOURTH)).isEqualTo(1);
        assertThat(result.get(Rank.NONE)).isEqualTo(1);
    }

    @Test
    void 수익률이_제대로_계산되는지_검증() {
        Service service = new Service();
        Map<Rank, Long> rankResults = new HashMap<>();
        rankResults.put(Rank.FIFTH, 1L);
        int purchaseAmount = 8000;

        double profitRate = service.calculateProfitRate(rankResults, purchaseAmount);

        assertThat(profitRate).isEqualTo(62.5);
    }
}
