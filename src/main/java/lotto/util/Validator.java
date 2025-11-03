package lotto.util;

import java.util.HashSet;
import java.util.List;

public class Validator {
    private final static int MIN = 1;
    private final static int MAX = 45;

    public static void validateNumbers(List<Integer> numbers) {
        duplicateNumber(numbers);
        rangeOfNumbers(numbers);
    }

    private static void duplicateNumber(List<Integer> numbers) {
        if (numbers.size() != new HashSet<>(numbers).size()) {
            throw new IllegalArgumentException("[ERROR] 중복된 번호는 입력할 수 없습니다.");
        }
    }

    private static void rangeOfNumbers(List<Integer> numbers) {
        boolean isInvalidRange = numbers.stream().anyMatch(n -> n < MIN || n > MAX);
        if (isInvalidRange) {
            throw new IllegalArgumentException("[ERROR] 1~45 사이의 번호만 입력할 수 있습니다.");
        }
    }

    public static void validatePurchaseAmount(int purchaseAmount) {
        multipleOfThousand(purchaseAmount);
        rangeOfPurchaseAmount(purchaseAmount);
    }

    private static void multipleOfThousand(int purchaseAmount) {
        if (purchaseAmount % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위로만 입력할 수 있습니다.");
        }
    }

    private static void rangeOfPurchaseAmount(int purchaseAmount) {
        if (purchaseAmount < 1000) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 이상 입력해야 합니다.");
        }
    }

    public static void validateBonusNumber(List<Integer> numbers, int bonusNumber) {
        isDuplicateBonusNumber(numbers, bonusNumber);
        rangeOfBonusNumber(bonusNumber);
    }

    private static void rangeOfBonusNumber(int bonusNumber) {
        if (bonusNumber > MAX || bonusNumber < MIN) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1~45 사이의 숫자만 입력 가능합니다.");
        }
    }

    private static void isDuplicateBonusNumber(List<Integer> numbers, int bonusNumber) {
        boolean duplicate = numbers.stream().anyMatch(n -> n == bonusNumber);
        if (duplicate) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호에 포함된 번호는 입력할 수 없습니다.");
        }
    }

}
