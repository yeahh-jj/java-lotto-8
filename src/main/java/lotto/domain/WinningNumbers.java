package lotto.domain;

import lotto.util.Validator;

public class WinningNumbers {
    private final Lotto winningNumbers;
    private final int bonusNumber;

    public WinningNumbers(Lotto winningNumbers, int bonusNumber) {
        Validator.validateBonusNumber(winningNumbers.getNumbers(), bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public Rank rankOf(Lotto oneLotto) {
        int matchCount = (int) oneLotto.getNumbers().stream()
                .filter(num -> winningNumbers.getNumbers().contains(num))
                .count();

        boolean matchBonusNumber = oneLotto.getNumbers().contains(bonusNumber);
        return Rank.valueOf(matchCount, matchBonusNumber);
    }
}
