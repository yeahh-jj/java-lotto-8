package lotto.util;

import java.util.HashSet;
import java.util.List;

public class Validator {
    private final static int MIN = 1;
    private final static int MAX = 45;

    public static void validateNumber(List<Integer> numbers) {
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
}
