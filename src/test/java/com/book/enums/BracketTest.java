package com.book.enums;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BracketTest {

    @Test
    public void firstBracketTest() {
        int sumFeedback = 40;
        assertEquals(Bracket.FIRST, Bracket.calculateBracket(sumFeedback));
    }

    @Test
    public void secondBracketTest() {
        int sumFeedback = 100;
        assertEquals(Bracket.SECOND, Bracket.calculateBracket(sumFeedback));
    }

    @Test
    public void thirdBracketTest() {
        int sumFeedback = 250;
        assertEquals(Bracket.THIRD, Bracket.calculateBracket(sumFeedback));
    }
}