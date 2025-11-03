package lotto.domain;

public enum Rank {
    FIRST(6, false, 2000000000),
    SECOND(5, true, 30000000),
    THIRD(5, false, 1500000),
    FOURTH(4, false, 50000),
    FIFTH(3, false, 5000),
    NONE(0, false, 0);

    private int matchCount;
    private boolean matchBonusNumber;
    private int prizeMoney;

    Rank(int matchCount, boolean matchBonusNumber, int prizeMoney) {
        this.matchCount = matchCount;
        this.matchBonusNumber = matchBonusNumber;
        this.prizeMoney = prizeMoney;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public static Rank valueOf(int matchCount, boolean matchBonusNumber) {
        if (matchCount == 6) return FIRST;
        if (matchCount == 5 && matchBonusNumber) return SECOND;
        if (matchCount == 5) return THIRD;
        if (matchCount == 4) return FOURTH;
        if (matchCount == 3) return FIFTH;
        return NONE;
    }
}
