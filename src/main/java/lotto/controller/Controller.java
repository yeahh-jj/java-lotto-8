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
    private Lotto winningLotto;

    public void run() {
        handlePurchaseAmount();
        handleWinningNumbers();
        showResultSafely();
    }

    private void handlePurchaseAmount() {
        while (true) {
            if (tryPurchase()) {
                return;
            }
        }
    }

    private boolean tryPurchase() {
        try {
            String input = InputView.inputPurchaseAmount();
            purchaseAmount = Parser.parsePurchaseAmount(input);
            userLottos = service.purchaseLottos(purchaseAmount);
            OutputView.outputPurchaseResult(userLottos);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private void handleWinningNumbers() {
        while (!tryWinningLotto()) {
        }
        while (!tryBonusNum()) {
        }
    }

    private boolean tryWinningLotto() {
        try {
            String inputWinning = InputView.inputWinningNumbers();
            List<Integer> winningNums = Parser.parseNumbers(inputWinning);
            winningLotto = new Lotto(winningNums);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private boolean tryBonusNum() {
        try {
            String inputBonus = InputView.inputBonusNumber();
            int bonusNumber = Parser.parseBonusNumber(inputBonus);
            winningNumbers = new WinningNumbers(winningLotto, bonusNumber);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
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
