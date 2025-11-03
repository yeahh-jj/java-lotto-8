package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.WinningNumbers;
import lotto.service.Service;
import org.junit.jupiter.api.Test;

public class LottoIntegrationTest {
    @Test
    void 로또_전체_흐름_테스트() {
        Service service = new Service();

        int purchaseAmount = 8000;
        List<Lotto> myLottos = List.of(
                new Lotto(List.of(8, 21, 23, 41, 42, 43)),
                new Lotto(List.of(3, 5, 11, 16, 32, 38)),
                new Lotto(List.of(7, 11, 16, 35, 36, 44)),
                new Lotto(List.of(1, 8, 11, 31, 41, 42)),
                new Lotto(List.of(13, 14, 16, 38, 42, 45)),
                new Lotto(List.of(7, 11, 30, 40, 42, 43)),
                new Lotto(List.of(2, 13, 22, 32, 38, 45)),
                new Lotto(List.of(1, 3, 5, 14, 22, 45))
        );

        WinningNumbers winningNumbers = new WinningNumbers(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7
        );

        Map<Rank, Long> rankResults = service.calculateRanks(myLottos, winningNumbers);
        double profitRate = service.calculateProfitRate(rankResults, purchaseAmount);

        assertThat(rankResults.get(Rank.FIFTH)).isEqualTo(1);
        assertThat(rankResults.get(Rank.NONE)).isEqualTo(7);

        long totalPrize = Rank.FIFTH.getPrizeMoney();
        double expectedRate = Math.round(((double) totalPrize / purchaseAmount * 100) * 10) / 10.0;

        assertThat(profitRate).isEqualTo(expectedRate);
    }
}
