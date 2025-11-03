package lotto.view;

import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.Rank;

public class OutputView {
    public static void outputPurchaseResult(List<Lotto> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    public static void outputTotalResult(Map<Rank, Long> rankResults, double profitRate) {
        System.out.println("당첨 통계");
        System.out.println("---");

        System.out.println("3개 일치 (5,000원) - " + rankResults.getOrDefault(Rank.FIFTH, 0L) + "개");
        System.out.println("4개 일치 (50,000원) - " + rankResults.getOrDefault(Rank.FOURTH, 0L) + "개");
        System.out.println("5개 일치 (1,500,000원) - " + rankResults.getOrDefault(Rank.THIRD, 0L) + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + rankResults.getOrDefault(Rank.SECOND, 0L) + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + rankResults.getOrDefault(Rank.FIRST, 0L) + "개");

        System.out.println("총 수익률은 " + profitRate + "%입니다.");
    }
}
