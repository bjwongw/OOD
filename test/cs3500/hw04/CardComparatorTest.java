package cs3500.hw04;

import cs3500.hw02.Card;
import cs3500.hw02.Rank;
import cs3500.hw02.Suit;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

/**
 * Tests for the CardComparator class
 */
public class CardComparatorTest {

  Comparator<Card> cardComparator = new CardComparator();
  Card kingClubs = new Card(Rank.King, Suit.Clubs);
  Card eightClubs = new Card(Rank.Eight, Suit.Clubs);
  Card threeClubs = new Card(Rank.Three, Suit.Clubs);
  Card kingDiamonds = new Card(Rank.King, Suit.Diamonds);
  Card fiveDiamonds = new Card(Rank.Five, Suit.Diamonds);
  Card aceHearts = new Card(Rank.Ace, Suit.Hearts);
  Card eightHearts = new Card(Rank.Eight, Suit.Hearts);
  Card sevenHearts = new Card(Rank.Seven, Suit.Hearts);
  Card fourHearts = new Card(Rank.Four, Suit.Hearts);
  Card queenSpades = new Card(Rank.Queen, Suit.Spades);
  Card nineSpades = new Card(Rank.Nine, Suit.Spades);
  Card threeSpades = new Card(Rank.Three, Suit.Spades);

  /**
   * Tests for the method compare
   */
  @Test
  public void testCompare() {
    assertEquals(-1, cardComparator.compare(kingClubs, eightClubs));
    assertEquals(-1, cardComparator.compare(kingClubs, kingDiamonds));
    assertEquals(-1, cardComparator.compare(eightClubs, aceHearts));
    assertEquals(-1, cardComparator.compare(sevenHearts, nineSpades));
    assertEquals(0, cardComparator.compare(eightClubs, eightClubs));
    assertEquals(0, cardComparator.compare(eightHearts, eightHearts));
    assertEquals(0, cardComparator.compare(fiveDiamonds, fiveDiamonds));
    assertEquals(0, cardComparator.compare(queenSpades, queenSpades));
    assertEquals(1, cardComparator.compare(threeSpades, fourHearts));
    assertEquals(1, cardComparator.compare(eightHearts, aceHearts));
    assertEquals(1, cardComparator.compare(threeClubs, eightClubs));
    assertEquals(1, cardComparator.compare(nineSpades, queenSpades));
  }
}
