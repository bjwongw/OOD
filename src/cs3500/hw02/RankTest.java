package cs3500.hw02;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the {@link Rank} enum
 */
public class RankTest {

  /**
   * Tests for the method getRankSymbol
   */
  @Test
  public void testGetRankSymbol() {
    assertEquals(Rank.Ace.getRankSymbol(), "A");
    assertEquals(Rank.King.getRankSymbol(), "K");
    assertEquals(Rank.Queen.getRankSymbol(), "Q");
    assertEquals(Rank.Jack.getRankSymbol(), "J");
    assertEquals(Rank.Ten.getRankSymbol(), "10");
    assertEquals(Rank.Nine.getRankSymbol(), "9");
    assertEquals(Rank.Eight.getRankSymbol(), "8");
    assertEquals(Rank.Seven.getRankSymbol(), "7");
    assertEquals(Rank.Six.getRankSymbol(), "6");
    assertEquals(Rank.Five.getRankSymbol(), "5");
    assertEquals(Rank.Four.getRankSymbol(), "4");
    assertEquals(Rank.Three.getRankSymbol(), "3");
    assertEquals(Rank.Two.getRankSymbol(), "2");
  }
}
