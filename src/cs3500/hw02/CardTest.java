package cs3500.hw02;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the {@link Card} class
 */
public class CardTest {

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
   * Tests for the method getRank
   */
  @Test
  public void testGetRank() {
    assertEquals(kingClubs.getRank(), Rank.King);
    assertEquals(threeClubs.getRank(), Rank.Three);
    assertEquals(eightDiamonds.getRank(), Rank.Eight);
    assertEquals(aceHearts.getRank(), Rank.Ace);
    assertEquals(queenSpades.getRank(), Rank.Queen);
    assertEquals(nineSpades.getRank(), Rank.Nine);
  }

  /**
   * Tests for the method getSuit
   */
  @Test
  public void testGetSuit() {
    assertEquals(eightClubs.getSuit(), Suit.Clubs);
    assertEquals(kingDiamonds.getSuit(), Suit.Diamonds);
    assertEquals(fiveDiamonds.getSuit(), Suit.Diamonds);
    assertEquals(eightHearts.getSuit(), Suit.Hearts);
    assertEquals(fourHearts.getSuit(), Suit.Hearts);
    assertEquals(threeSpades.getSuit(), Suit.Spades);
    assertEquals(nineSpades.getSuit(), Suit.Spades);
    assertEquals(threeClubs.getSuit(), Suit.Clubs);
  }

  /**
   * Tests for the method printCard
   */
  @Test
  public void printCard() {
    assertEquals(kingClubs.printCard(), "K♣");
    assertEquals(queenSpades.printCard(), "Q♠");
    assertEquals(aceHearts.printCard(), "A♥");
    assertEquals(sevenHearts.printCard(), "7♥");
    assertEquals(nineSpades.printCard(), "9♠");
    assertEquals(eightDiamonds.printCard(), "8♦");
    assertEquals(threeClubs.printCard(), "3♣");
    assertEquals(fiveDiamonds.printCard(), "5♦");
  }

  /**
   * Tests for the method equals
   */
  @Test
  public void testEquals() {
    assertEquals(kingClubs.equals(kingClubs), true);
    assertEquals(kingClubs.equals(new Card(Rank.King, Suit.Clubs)), true);
    assertEquals(new Card(Rank.King, Suit.Clubs).equals(kingClubs), true);
    assertEquals(sevenHearts.equals(sevenHearts), true);
    assertEquals(sevenHearts.equals(new Card(Rank.Seven, Suit.Hearts)), true);
    assertEquals(new Card(Rank.Seven, Suit.Hearts).equals(sevenHearts), true);
    assertEquals(new Card(Rank.Seven, Suit.Hearts).equals(new Card(Rank.Seven, Suit.Hearts)), true);
    assertEquals(kingClubs.equals(eightDiamonds), false);
    assertEquals(eightDiamonds.equals(kingClubs), false);
    assertEquals(fiveDiamonds.equals(eightDiamonds), false);
    assertEquals(fiveDiamonds.equals(aceHearts), false);
    assertEquals(eightDiamonds.equals(eightClubs), false);
  }

  /**
   * Tests for the method hashCode
   */
  @Test
  public void testHashCode() {
    assertEquals(kingClubs.hashCode(), new Card(Rank.King, Suit.Clubs).hashCode());
    assertEquals(new Card(Rank.Ace, Suit.Hearts).hashCode(),
      new Card(Rank.Ace, Suit.Hearts).hashCode());
    assertEquals(new Card(Rank.Three, Suit.Clubs).hashCode(), threeClubs.hashCode());
    assertEquals(eightDiamonds.hashCode(), new Card(Rank.Eight, Suit.Diamonds).hashCode());
    assertEquals(nineSpades.hashCode(), new Card(Rank.Nine, Suit.Spades).hashCode());
    assertEquals(new Card(Rank.Six, Suit.Diamonds).hashCode(),
      new Card(Rank.Six, Suit.Diamonds).hashCode());
  }
}
