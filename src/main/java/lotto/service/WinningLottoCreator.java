package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class WinningLottoCreator {

    public List<Integer> createWinningNumbers() {
        return Randoms.pickUniqueNumbersInRange(1, 45, 6);
    }

    public int createBonusNumber(List<Integer> winningNumbers) {
        int bonusNum;
        do {
            bonusNum = Randoms.pickNumberInRange(1, 45);
        } while (winningNumbers.contains(bonusNum));
        return bonusNum;
    }
}
