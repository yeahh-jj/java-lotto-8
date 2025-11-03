package lotto.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.WinningNumbers;
import lotto.util.Validator;

public class Service {
    private static final int PRICE = 1000;

    public List<Lotto> purchaseLottos(int purchaseAmount) {
        Validator.validatePurchaseAmount(purchaseAmount);
        int count = purchaseAmount / PRICE;

        return IntStream.range(0, count)
                .mapToObj(i -> new Lotto(LottoNumberGenerator.createWinningNumbers()))
                .toList();
    }

    public Map<Rank, Long> calculateRanks(List<Lotto> lottos, WinningNumbers winningNumbers) {
        Map<Rank, Long> rankResult = new HashMap<>();

        for (Lotto lotto : lottos) {
            Rank rank = winningNumbers.rankOf(lotto);
            rankResult.put(rank, rankResult.getOrDefault(rank, 0L) + 1);
        }

        return rankResult;
    }

    public double calculateProfitRate(Map<Rank, Long> rankResults, int purchaseAmount) {
        long totalPrize = 0;

        for (Map.Entry<Rank, Long> entry : rankResults.entrySet()) {
            Rank rank = entry.getKey();
            long count = entry.getValue();
            totalPrize += rank.getPrizeMoney() * count;
        }

        double profitRate = (double) totalPrize / purchaseAmount * 100;
        return Math.round(profitRate * 10) / 10.0;
    }
}
