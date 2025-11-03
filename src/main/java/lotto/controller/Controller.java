package lotto.controller;

import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.WinningNumbers;
import lotto.service.Service;
import lotto.util.Parser;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Controller {
    private final Service service = new Service();
    private int purchaseAmount;
    private List<Lotto> userLottos;
    private WinningNumbers winningNumbers;

    public void run() {
        handlePurchaseAmount();
        handleWinningNumbers();
        showResultSafely();
    }

    private void handlePurchaseAmount() {
        while (true) {
            try {
                String input = InputView.inputPurchaseAmount();
                purchaseAmount = Parser.parsePurchaseAmount(input);
                userLottos = service.purchaseLottos(purchaseAmount);
                OutputView.outputPurchaseResult(userLottos);
                return;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void handleWinningNumbers() {
        Lotto winningLotto = null;

        while (winningLotto == null) {
            try {
                String inputWinning = InputView.inputWinningNumbers();
                List<Integer> winningNums = Parser.parseNumbers(inputWinning);
                winningLotto = new Lotto(winningNums);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                String inputBonus = InputView.inputBonusNumber();
                int bonusNumber = Parser.parseBonusNumber(inputBonus);
                winningNumbers = new WinningNumbers(winningLotto, bonusNumber);
                return;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void showResultSafely() {
        try {
            Map<Rank, Long> rankResults = service.calculateRanks(userLottos, winningNumbers);
            double profitRate = service.calculateProfitRate(rankResults, purchaseAmount);
            OutputView.outputTotalResult(rankResults, profitRate);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
