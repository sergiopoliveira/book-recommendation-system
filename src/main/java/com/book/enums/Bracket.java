package com.book.enums;

public enum Bracket {
    FIRST, SECOND, THIRD;

    /*
     * 1. First bracket - if total values added <50, only 30% of recommended books
     * reflect those genres preference, rest is random;
     * 2. Second bracket - if total
     * values added >=50 and <150 then system recommends 50% of books on t, rest
     * random;
     *  3. Third bracket - if total values added >=150 then system recommends
     * 80% books on those genres, rest random.
     *
     */
    public static Bracket calculateBracket(int sumFeedback) {
        if (sumFeedback < 50) return Bracket.FIRST;
        if (sumFeedback >= 50 && sumFeedback < 150) return Bracket.SECOND;
        else return Bracket.THIRD;
    }

}
