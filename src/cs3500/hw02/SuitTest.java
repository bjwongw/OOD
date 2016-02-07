package cs3500.hw02;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the {@link Suit} enum
 */
public class SuitTest {

  /**
   * Tests for the method getSuitSymbol
   */
  @Test
  public void testGetSuitSymbol() {
    assertEquals(Suit.Clubs.getSuitSymbol(), "♣");
    assertEquals(Suit.Diamonds.getSuitSymbol(), "♦");
    assertEquals(Suit.Hearts.getSuitSymbol(), "♥");
    assertEquals(Suit.Spades.getSuitSymbol(), "♠");
  }
}
