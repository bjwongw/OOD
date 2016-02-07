package cs3500.hw02;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the comparator class AbcSuitAceHighDesc
 */
public class AbcSuitAceHighDescTest {
  AbcSuitAceHighDesc abc = new AbcSuitAceHighDesc();
  Card kingClubs = new Card(Rank.King, Suit.Clubs);
  Card eightClubs = new Card(Rank.Eight, Suit.Clubs);
  Card threeClubs = new Card(Rank.Three, Suit.Clubs);
  Card kingDiamonds = new Card(Rank.King, Suit.Diamonds);
  Card eightDiamonds = new Card(Rank.Eight, Suit.Diamonds);
  Card fiveDiamonds = new Card(Rank.Five, Suit.Diamonds);
  Card aceHearts = new Card(Rank.Ace, Suit.Hearts);
  Card eightHearts = new Card(Rank.Eight, Suit.Hearts);
  Card sevenHearts = new Card(Rank.Seven, Suit.Hearts);
  Card fourHearts = new Card(Rank.Four, Suit.Hearts);
  Card queenSpades = new Card(Rank.Queen, Suit.Spades);
  Card nineSpades = new Card(Rank.Nine, Suit.Spades);
  Card threeSpades = new Card(Rank.Three, Suit.Spades);

  /**
   * Tests for the method compare when the given Cards' suits are the same
   */
  @Test
  public void testCompare_sameSuit() {
    assertEquals(abc.compare(kingClubs, threeClubs), -1);
    assertEquals(abc.compare(threeClubs, kingClubs), 1);
    assertEquals(abc.compare(eightClubs, kingClubs), 1);
    assertEquals(abc.compare(fiveDiamonds, eightDiamonds), 1);
    assertEquals(abc.compare(eightDiamonds, fiveDiamonds), -1);
    assertEquals(abc.compare(kingDiamonds, fiveDiamonds), -1);
    assertEquals(abc.compare(aceHearts, sevenHearts), -1);
    assertEquals(abc.compare(fourHearts, sevenHearts), 1);
    assertEquals(abc.compare(queenSpades, threeSpades), -1);
    assertEquals(abc.compare(threeSpades, nineSpades), 1);
  }

  /**
   * Tests for the method compare when the given Cards' ranks are the same
   */
  @Test
  public void testCompare_sameRank() {
    assertEquals(abc.compare(kingClubs, kingDiamonds), -1);
    assertEquals(abc.compare(kingDiamonds, kingClubs), 1);
    assertEquals(abc.compare(threeClubs, threeSpades), -1);
    assertEquals(abc.compare(threeSpades, threeClubs), 1);
    assertEquals(abc.compare(eightClubs, eightHearts), -1);
    assertEquals(abc.compare(eightHearts, eightDiamonds), 1);
  }

  /**
   * Tests for the method compare when the given Cards' ranks and suits are the same
   */
  @Test
  public void testCompare_sameSuitAndRank() {
    assertEquals(abc.compare(kingClubs, kingClubs), 0);
    assertEquals(abc.compare(eightHearts, eightHearts), 0);
    assertEquals(abc.compare(sevenHearts, sevenHearts), 0);
    assertEquals(abc.compare(nineSpades, nineSpades), 0);
    assertEquals(abc.compare(queenSpades, queenSpades), 0);
    assertEquals(abc.compare(kingDiamonds, kingDiamonds), 0);
  }

  /**
   * Tests for the method compare when the given Cards' ranks and suits are different
   */
  @Test
  public void testCompare_allDifferent() {
    assertEquals(abc.compare(kingClubs, eightHearts), -1);
    assertEquals(abc.compare(kingClubs, eightHearts), -1);
    assertEquals(abc.compare(kingClubs, eightHearts), -1);
    assertEquals(abc.compare(kingClubs, eightHearts), -1);
    assertEquals(abc.compare(kingClubs, eightHearts), -1);
    assertEquals(abc.compare(kingClubs, eightHearts), -1);
    assertEquals(abc.compare(kingClubs, eightHearts), -1);
    assertEquals(abc.compare(kingClubs, eightHearts), -1);
  }
}
