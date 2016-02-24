package cs3500.hw02;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the {@link Suit} enum
 */
public class SuitTest {

  /**
   * Tests for the method toString
   */
  @Test
  public void testToString() {
    assertEquals(Suit.Clubs.toString(), "♣");
    assertEquals(Suit.Diamonds.toString(), "♦");
    assertEquals(Suit.Hearts.toString(), "♥");
    assertEquals(Suit.Spades.toString(), "♠");
  }
}
