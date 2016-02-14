package cs3500.hw02;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests for the GenericStandardDeckGame class.
 */
public class GenericStandardDeckGameTest {

  GenericStandardDeckGame game = new GenericStandardDeckGame();

  /**
   * Tests for the method getPlayers
   */
  @Test
  public void testGetPlayers() {
    GenericStandardDeckGame game1 = new GenericStandardDeckGame();
    GenericStandardDeckGame game2 = new GenericStandardDeckGame();

    assertEquals(game1.getPlayers(), new ArrayList<Player>());
    assertEquals(game1.getPlayers(), game2.getPlayers());
    assertEquals(game1.getPlayers().size(), 0);
    assertEquals(game2.getPlayers().size(), 0);
    game1.startPlay(2, game1.getDeck());
    assertNotEquals(game1.getPlayers(), new ArrayList<Player>());
    assertNotEquals(game1.getPlayers(), game2.getPlayers());
    assertEquals(game1.getPlayers().size(), 2);
    game2.startPlay(10, game2.getDeck());
    assertNotEquals(game2.getPlayers(), new ArrayList<Player>());
    assertEquals(game2.getPlayers().size(), 10);
  }

  /**
   * Tests the method getDeck.
   * <p>Checks that the size of the deck is 52 (standard size), then makes sure
   * that the cards are in the expected positions. The returned deck is always
   * ordered by suit in alphabetical order, then rank in descending order
   * starting with Aces high.</p>
   */
  @Test
  public void testGetDeck() {
    List<Card> standardDeck = game.getDeck();
    assertEquals(standardDeck.size(), 52);
    assertEquals(standardDeck.get(0), new Card(Rank.Ace, Suit.Clubs));
    assertEquals(standardDeck.get(2), new Card(Rank.Queen, Suit.Clubs));
    assertEquals(standardDeck.get(4), new Card(Rank.Ten, Suit.Clubs));
    assertEquals(standardDeck.get(6), new Card(Rank.Eight, Suit.Clubs));
    assertEquals(standardDeck.get(8), new Card(Rank.Six, Suit.Clubs));
    assertEquals(standardDeck.get(10), new Card(Rank.Four, Suit.Clubs));
    assertEquals(standardDeck.get(12), new Card(Rank.Two, Suit.Clubs));
    assertEquals(standardDeck.get(14), new Card(Rank.King, Suit.Diamonds));
    assertEquals(standardDeck.get(16), new Card(Rank.Jack, Suit.Diamonds));
    assertEquals(standardDeck.get(18), new Card(Rank.Nine, Suit.Diamonds));
    assertEquals(standardDeck.get(20), new Card(Rank.Seven, Suit.Diamonds));
    assertEquals(standardDeck.get(25), new Card(Rank.Two, Suit.Diamonds));
    assertEquals(standardDeck.get(26), new Card(Rank.Ace, Suit.Hearts));
    assertEquals(standardDeck.get(27), new Card(Rank.King, Suit.Hearts));
    assertEquals(standardDeck.get(29), new Card(Rank.Jack, Suit.Hearts));
    assertEquals(standardDeck.get(31), new Card(Rank.Nine, Suit.Hearts));
    assertEquals(standardDeck.get(33), new Card(Rank.Seven, Suit.Hearts));
    assertEquals(standardDeck.get(39), new Card(Rank.Ace, Suit.Spades));
    assertEquals(standardDeck.get(42), new Card(Rank.Jack, Suit.Spades));
    assertEquals(standardDeck.get(45), new Card(Rank.Eight, Suit.Spades));
    assertEquals(standardDeck.get(47), new Card(Rank.Six, Suit.Spades));
    assertEquals(standardDeck.get(50), new Card(Rank.Three, Suit.Spades));
    assertEquals(standardDeck.get(51), new Card(Rank.Two, Suit.Spades));
  }

  /**
   * Tests the method startPlay, validating the effectiveness of isStandardDeck.
   * <p>Ensures that startPlay cannot accept an empty deck</p>
   */
  @Test(expected = IllegalArgumentException.class)
  public void startPlayEmptyDeck() {
    game.startPlay(2, new ArrayList<>());
  }

  /**
   * Tests the method startPlay, validating the effectiveness of isStandardDeck.
   * <p>Ensures that startPlay cannot accept a deck smaller than 52 cards</p>
   */
  @Test(expected = IllegalArgumentException.class)
  public void startPlaySmallDeck() {
    List<Card> deck = game.getDeck();
    deck.remove(0);
    game.startPlay(2, deck);
  }

  /**
   * Tests the method startPlay, validating the effectiveness of isStandardDeck.
   * <p>Ensures that startPlay cannot accept a deck larger than 52 cards</p>
   */
  @Test(expected = IllegalArgumentException.class)
  public void startPlayLargeDeck() {
    List<Card> deck = game.getDeck();
    deck.add(new Card(Rank.Ace, Suit.Clubs));
    game.startPlay(2, deck);
  }

  /**
   * Tests the method startPlay, validating the effectiveness of isStandardDeck.
   * <p>Ensures that startPlay cannot accept a deck with duplicate cards</p>
   */
  @Test (expected = IllegalArgumentException.class)
  public void startPlayDuplicateCards() {
    List<Card> deck = game.getDeck();
    deck.set(1, new Card(Rank.Ace, Suit.Clubs));
    deck.set(2, new Card(Rank.Ace, Suit.Clubs));
    game.startPlay(2, deck);
  }

  /**
   * Tests the method startPlay, validating the effectiveness of isStandardDeck.
   * <p>Ensures that startPlay cannot accept a null deck</p>
   */
  @Test (expected = IllegalArgumentException.class)
  public void startPlayNullDeck() {
    game.startPlay(2, null);
  }

  /**
   * Tests the method startPlay.
   * <p>Ensures that an exception will be thrown if the given number of Players is 1.</p>
   */
  @Test (expected = IllegalArgumentException.class)
  public void startPlayOnePlayer() {
    game.startPlay(1, game.getDeck());
  }

  /**
   * Tests the method startPlay.
   * <p>Ensures that an exception will be thrown if the given number of Players is less than 1.</p>
   */
  @Test (expected = IllegalArgumentException.class)
  public void startPlayLessThanOnePlayer() {
    game.startPlay(0, game.getDeck());
  }

  /**
   * Tests getGameState after an exception is thrown by startPlay.
   */
  @Test
  public void testGetGameState_startPlayThrewException() {
    assertEquals(game.getGameState(), "Number of Players: 0");
    try {
      game.startPlay(0, game.getDeck());
    } catch (IllegalArgumentException ex) {
      assertEquals(game.getGameState(), "Number of Players: 0");
    }
  }

  /**
   * Tests for the method startPlay using various amounts of players
   */
  @Test
  public void testStartPlay() {
    GenericStandardDeckGame game = new GenericStandardDeckGame();
    assertEquals(game.getPlayers().size(), 0);

    game.startPlay(2, game.getDeck());
    assertEquals(game.getDeck().size(), 52);
    assertEquals(game.getPlayers().size(), 2);
    List<Player> players = game.getPlayers();
    assertEquals(players.get(0).getHand().size(), 26);
    assertEquals(players.get(1).getHand().size(), 26);

    game.startPlay(3, game.getDeck());
    assertEquals(game.getDeck().size(), 52);
    assertEquals(game.getPlayers().size(), 3);
    players = game.getPlayers();
    assertEquals(players.get(0).getHand().size(), 18);
    assertEquals(players.get(1).getHand().size(), 17);
    assertEquals(players.get(2).getHand().size(), 17);
    assertEquals(players.get(0).getHand().get(0), new Card(Rank.Ace, Suit.Clubs));
    assertEquals(players.get(1).getHand().get(0), new Card(Rank.King, Suit.Clubs));
    assertEquals(players.get(2).getHand().get(16), new Card(Rank.Three, Suit.Spades));

    game.startPlay(54, game.getDeck());
    assertEquals(game.getDeck().size(), 52);
    assertEquals(game.getPlayers().size(), 54);
    players = game.getPlayers();
    assertEquals(players.get(0).getHand().size(), 1);
    assertEquals(players.get(51).getHand().size(), 1);
    assertEquals(players.get(52).getHand().size(), 0);
    assertEquals(players.get(0).getHand().get(0), new Card(Rank.Ace, Suit.Clubs));
    assertEquals(players.get(1).getHand().get(0), new Card(Rank.King, Suit.Clubs));
    assertEquals(players.get(51).getHand().get(0), new Card(Rank.Two, Suit.Spades));
  }

  /**
   * Test for the method startPlay using an unordered deck
   */
  @Test
  public void testStartPlay_UnorderedDeck() {
    GenericStandardDeckGame game = new GenericStandardDeckGame();
    List<Card> unorderedDeck = game.getDeck();
    assertEquals(unorderedDeck, game.getDeck());
    Card temp = unorderedDeck.get(51);
    unorderedDeck.set(51, unorderedDeck.get(0));
    unorderedDeck.set(0, temp);
    assertNotEquals(unorderedDeck, game.getDeck());
    game.startPlay(2, unorderedDeck);
    List<Player> players = game.getPlayers();
    assertEquals(players.get(0).getHand().get(0), new Card(Rank.Two, Suit.Spades));
    assertEquals(players.get(1).getHand().get(25), new Card(Rank.Ace, Suit.Clubs));
  }

  /**
   * Tests for the method startPlay. Tests that startPlay actually uses the given deck.
   * Checks the accuracy of this by examining the getGameState return.
   */
  @Test
  public void testStartPlay_changeInputDeck() {
    GenericStandardDeckGame game = new GenericStandardDeckGame();
    String newline = System.getProperty("line.separator");
    List<Card> gameDeck = game.getDeck();
    Card lastCard = gameDeck.get(51); // 2 of Spades
    Card firstCard = gameDeck.get(0); // Ace of Clubs
    gameDeck.remove(51);
    gameDeck.remove(0);
    gameDeck.add(0, lastCard); // the first Card is now 2 of Spades
    gameDeck.add(firstCard); // the last Card is now Ace of Clubs

    game.startPlay(4, gameDeck);
    assertEquals(game.getGameState(), "Number of Players: 4" + newline +
      "Player 1: 10♣, 6♣, 2♣, J♦, 7♦, 3♦, Q♥, 8♥, 4♥, K♠, 9♠, 5♠, 2♠" + newline +
      "Player 2: K♣, 9♣, 5♣, A♦, 10♦, 6♦, 2♦, J♥, 7♥, 3♥, Q♠, 8♠, 4♠" + newline +
      "Player 3: Q♣, 8♣, 4♣, K♦, 9♦, 5♦, A♥, 10♥, 6♥, 2♥, J♠, 7♠, 3♠" + newline +
      "Player 4: A♣, J♣, 7♣, 3♣, Q♦, 8♦, 4♦, K♥, 9♥, 5♥, A♠, 10♠, 6♠");
  }

  /**
   * Tests for the method getGameState
   */
  @Test
  public void testGetGameState() {
    GenericStandardDeckGame game = new GenericStandardDeckGame();
    String newline = System.getProperty("line.separator");
    assertEquals(game.getGameState(), "Number of Players: 0");

    game.startPlay(2, game.getDeck());
    assertEquals(game.getGameState(), "Number of Players: 2" + newline +
      "Player 1: A♣, Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥, 8♥, 6♥, 4♥, " +
      "2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠" + newline + "Player 2: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, " +
      "8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠");

    game.startPlay(4, game.getDeck());
    assertEquals(game.getGameState(), "Number of Players: 4" + newline +
      "Player 1: A♣, 10♣, 6♣, 2♣, J♦, 7♦, 3♦, Q♥, 8♥, 4♥, K♠, 9♠, 5♠" + newline +
      "Player 2: K♣, 9♣, 5♣, A♦, 10♦, 6♦, 2♦, J♥, 7♥, 3♥, Q♠, 8♠, 4♠" + newline +
      "Player 3: Q♣, 8♣, 4♣, K♦, 9♦, 5♦, A♥, 10♥, 6♥, 2♥, J♠, 7♠, 3♠" + newline +
      "Player 4: J♣, 7♣, 3♣, Q♦, 8♦, 4♦, K♥, 9♥, 5♥, A♠, 10♠, 6♠, 2♠");
  }

  /**
   * Tests for the method getGameState to ensure that the Collections.sort() call on the Player's
   * hand is not changing the actual ordering of their cards.
   */
  @Test
  public void testGetGameState_notChangingPlayerHand() {
    GenericStandardDeckGame game = new GenericStandardDeckGame();
    game.startPlay(2, game.getDeck());
    Player player1 = game.getPlayers().get(0);
    assertEquals(player1.getHand().get(0), new Card(Rank.Ace, Suit.Clubs));
    player1.getHand().set(0, new Card(Rank.Two, Suit.Clubs));
    assertEquals(player1.getHand().get(0), new Card(Rank.Two, Suit.Clubs));
    game.getGameState();
    assertEquals(player1.getHand().get(0), new Card(Rank.Two, Suit.Clubs));

    game.startPlay(4, game.getDeck());
    Player player2 = game.getPlayers().get(0);
    assertEquals(player2.getHand().get(2), new Card(Rank.Six, Suit.Clubs));
    player2.getHand().set(2, new Card(Rank.Four, Suit.Spades));
    assertEquals(player2.getHand().get(2), new Card(Rank.Four, Suit.Spades));
    game.getGameState();
    assertEquals(player2.getHand().get(2), new Card(Rank.Four, Suit.Spades));
  }
}
