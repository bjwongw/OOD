package cs3500.hw02;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests for the {@link Player} class
 */
public class PlayerTest {

  Player p1 = new Player();
  Player p2 = new Player();
  Card aceHearts = new Card(Rank.Ace, Suit.Hearts);
  Card kingDiamonds = new Card(Rank.King, Suit.Diamonds);
  Card threeSpades = new Card(Rank.Three, Suit.Spades);
  Card sevenClubs = new Card(Rank.Seven, Suit.Clubs);
  Card nineDiamonds = new Card(Rank.Nine, Suit.Diamonds);

  /**
   * Tests for the method getHand
   */
  @Test
  public void testGetHand() {
    List<Card> cardSet = new ArrayList<>();
    assertEquals(p1.getHand(), cardSet);
    p1.addToHand(aceHearts);
    assertNotEquals(p1.getHand(), cardSet);
    cardSet.add(aceHearts);
    assertEquals(p1.getHand(), cardSet);
    p1.addToHand(kingDiamonds);
    cardSet.add(kingDiamonds);
    assertEquals(p1.getHand(), cardSet);
  }

  /**
   * Tests for the method addToHand
   */
  @Test
  public void testAddToHand() {
    assertEquals(p2.getHand().size(), 0);
    p2.addToHand(threeSpades);
    assertEquals(p2.getHand().size(), 1);
    assertEquals(p2.getHand().get(0), threeSpades);
    p2.addToHand(sevenClubs);
    assertEquals(p2.getHand().size(), 2);
    assertEquals(p2.getHand().get(1), sevenClubs);
    p2.addToHand(nineDiamonds);
    assertEquals(p2.getHand().size(), 3);
    assertEquals(p2.getHand().get(2), nineDiamonds);
    p2.addToHand(aceHearts);
    assertEquals(p2.getHand().size(), 4);
    assertEquals(p2.getHand().get(3), aceHearts);
    p2.addToHand(kingDiamonds);
    assertEquals(p2.getHand().size(), 5);
    assertEquals(p2.getHand().get(4), kingDiamonds);
    p2.addToHand(nineDiamonds);
    assertEquals(p2.getHand().size(), 6);
    assertEquals(p2.getHand().get(5), nineDiamonds);
  }

  /**
   * Tests for the methods addWins and getWins
   */
  @Test
  public void testAddWinsGetWins() {
    Player p1 = new Player();
    Player p2 = new Player();
    assertEquals(p1.getWins(), 0);
    assertEquals(p2.getWins(), 0);
    p1.addWin();
    assertEquals(p1.getWins(), 1);
    p2.addWin();
    assertEquals(p2.getWins(), 1);
    p2.addWin();
    assertEquals(p2.getWins(), 2);
  }
}
