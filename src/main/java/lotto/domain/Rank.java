package lotto.domain;

public enum Rank {
    FIRST(6, false, 2000000000),
    SECOND(5, true, 30000000),
    THIRD(5, false, 1500000),
    FOURTH(4, false, 50000),
    FIFTH(3, false, 5000);

    private int matchNumber;
    private boolean matchBonusNumber;
    private int prizeMoney;

    Rank(int matchNumber, boolean matchBonusNumber, int prizeMoney) {
        this.matchNumber = matchNumber;
        this.matchBonusNumber = matchBonusNumber;
        this.prizeMoney = prizeMoney;
    }
}
