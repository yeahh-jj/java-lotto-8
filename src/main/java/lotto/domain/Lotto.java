package lotto.domain;

import java.util.List;
import lotto.util.Validator;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        Validator.validateNumbers(numbers);
        this.numbers = numbers.stream()
                .sorted()
                .toList();
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
