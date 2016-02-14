package cs3500.hw02;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the {@link Rank} enum
 */
public class RankTest {

  /**
   * Tests for the method toString
   */
  @Test
  public void testToString() {
    assertEquals(Rank.Ace.toString(), "A");
    assertEquals(Rank.King.toString(), "K");
    assertEquals(Rank.Queen.toString(), "Q");
    assertEquals(Rank.Jack.toString(), "J");
    assertEquals(Rank.Ten.toString(), "10");
    assertEquals(Rank.Nine.toString(), "9");
    assertEquals(Rank.Eight.toString(), "8");
    assertEquals(Rank.Seven.toString(), "7");
    assertEquals(Rank.Six.toString(), "6");
    assertEquals(Rank.Five.toString(), "5");
    assertEquals(Rank.Four.toString(), "4");
    assertEquals(Rank.Three.toString(), "3");
    assertEquals(Rank.Two.toString(), "2");
  }
}
