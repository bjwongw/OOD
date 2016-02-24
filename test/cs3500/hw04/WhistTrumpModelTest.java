package cs3500.hw04;

import cs3500.hw02.Card;
import cs3500.hw02.Suit;
import cs3500.hw03.CardGameModel;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the WhistTrumpModel class
 */
public class WhistTrumpModelTest {

  /**
   * Tests the method startPlay, making sure an exception is thrown when there are too few
   * players given to start the game
   */
  @Test(expected = IllegalArgumentException.class)
  public void startPlay_onePlayer() {
    CardGameModel<Card> trump = new WhistTrumpModel();
    trump.startPlay(1, trump.getDeck());
  }

  /**
   * Tests the method play, making sure an exception is thrown when the playerNo given is
   * not the current player
   */
  @Test(expected = IllegalArgumentException.class)
  public void playWrongPlayerIndex() {
    CardGameModel<Card> trump = new WhistTrumpModel();
    trump.startPlay(3, trump.getDeck());
    trump.play(7, 0);
  }

  /**
   * Tests the method play, making sure an exception is thrown when the position cardIdx is
   * greater than the highest index in the player's hand
   */
  @Test(expected = IllegalArgumentException.class)
  public void playInvalidCardIndex_highIndex() {
    CardGameModel<Card> trump = new WhistTrumpModel();
    trump.startPlay(4, trump.getDeck());
    trump.play(0, 14);
  }

  /**
   * Tests the method play, making sure an exception is thrown when the position cardIdx is
   * less than 0
   */
  @Test(expected = IllegalArgumentException.class)
  public void playInvalidCardIndex_negativeIndex() {
    CardGameModel<Card> trump = new WhistTrumpModel();
    trump.startPlay(7, trump.getDeck());
    trump.play(0, -1);
  }

  /**
   * Tests the method play, making sure an exception is thrown when the current card being played
   * is of the wrong suit, even though the player has a card of the correct suit. You can
   * only play a trump card when out of cards with the suit matching the hand's suit.
   */
  @Test(expected = IllegalArgumentException.class)
  public void playWrongSuit_illegalTrump() {
    CardGameModel<Card> trump = new WhistTrumpModel();
    trump.startPlay(9, trump.getDeck());
    trump.play(0, 2); // first player plays Nine of Diamonds
    trump.play(1, 1); // second player tries to play Four of Clubs (trump) even though
                      // he has a Diamonds card
  }

  /**
   * Tests the method startPlay, making sure that the trump suit is changed when the method is
   * called.
   */
  @Test
  public void testStartPlay_trumpFieldClubs() {
    CardGameModel<Card> trump = new WhistTrumpModel();
    List<Card> deck = trump.getDeck();
    assertEquals(Suit.Clubs, deck.get(0).getSuit());
    trump.startPlay(3, deck);
    String gameState = trump.getGameState();
    String trumpSuit = gameState.substring(gameState.length() - 1);
    assertEquals(deck.get(0).getSuit().toString(), trumpSuit);
  }

  /**
   * Tests the method startPlay, making sure that the trump suit is changed when the method is
   * called, and that the trump suit is that of the first card in the deck
   */
  @Test
  public void testStartPlay_trumpFieldSpades() {
    CardGameModel<Card> trump = new WhistTrumpModel();
    List<Card> deck = trump.getDeck();
    Card lastCard = deck.remove(deck.size() - 1);
    deck.add(0, lastCard);
    assertEquals(Suit.Spades, deck.get(0).getSuit());
    trump.startPlay(5, deck);
    String gameState = trump.getGameState();
    String trumpSuit = gameState.substring(gameState.length() - 1);
    assertEquals(deck.get(0).getSuit().toString(), trumpSuit);
  }

  /**
   * Tests the method getGameState when there are no players in the game.
   */
  @Test
  public void testGetGameState_noPlayers() {
    CardGameModel<Card> trump = new WhistTrumpModel();
    assertEquals("Number of Players: 0\nGame over. Player  won.\nTrump: ", trump.getGameState());
  }

  /**
   * Tests the method getGameState with six players. Has checks to make sure the trump aspect
   * of the game is properly implemented.
   */
  @Test
  public void testGetGameState_sixPlayers() {
    CardGameModel<Card> trump = new WhistTrumpModel();
    trump.startPlay(6, trump.getDeck());
    String gameState = "Number of Players: 6\n" + "Player 1: A♣, 8♣, 2♣, 9♦, 3♦, 10♥, 4♥, J♠, 5♠\n"
      + "Player 2: K♣, 7♣, A♦, 8♦, 2♦, 9♥, 3♥, 10♠, 4♠\n"
      + "Player 3: Q♣, 6♣, K♦, 7♦, A♥, 8♥, 2♥, 9♠, 3♠\n"
      + "Player 4: J♣, 5♣, Q♦, 6♦, K♥, 7♥, A♠, 8♠, 2♠\n"
      + "Player 5: 10♣, 4♣, J♦, 5♦, Q♥, 6♥, K♠, 7♠\n"
      + "Player 6: 9♣, 3♣, 10♦, 4♦, J♥, 5♥, Q♠, 6♠\n" + "Player 1: 0 hands won\n"
      + "Player 2: 0 hands won\n" + "Player 3: 0 hands won\n" + "Player 4: 0 hands won\n"
      + "Player 5: 0 hands won\n" + "Player 6: 0 hands won\n" + "Turn: Player 1\n" + "Trump: ♣";
    assertEquals(trump.getCurrentPlayer(), 0);
    assertEquals(gameState, trump.getGameState());
    trump.play(0, 4);
    assertEquals(trump.getCurrentPlayer(), 1);
    trump.play(1, 4);
    assertEquals(trump.getCurrentPlayer(), 2);
    trump.play(2, 3);
    assertEquals(trump.getCurrentPlayer(), 3);
    trump.play(3, 3);
    assertEquals(trump.getCurrentPlayer(), 4);
    trump.play(4, 3);
    assertEquals(trump.getCurrentPlayer(), 5);
    trump.play(5, 3);
    gameState = "Number of Players: 6\n" + "Player 1: A♣, 8♣, 2♣, 9♦, 10♥, 4♥, J♠, 5♠\n"
      + "Player 2: K♣, 7♣, A♦, 8♦, 9♥, 3♥, 10♠, 4♠\n" + "Player 3: Q♣, 6♣, K♦, A♥, 8♥, 2♥, 9♠, 3♠\n"
      + "Player 4: J♣, 5♣, Q♦, K♥, 7♥, A♠, 8♠, 2♠\n" + "Player 5: 10♣, 4♣, J♦, Q♥, 6♥, K♠, 7♠\n"
      + "Player 6: 9♣, 3♣, 10♦, J♥, 5♥, Q♠, 6♠\n" + "Player 1: 0 hands won\n"
      + "Player 2: 0 hands won\n" + "Player 3: 1 hands won\n" + "Player 4: 0 hands won\n"
      + "Player 5: 0 hands won\n" + "Player 6: 0 hands won\n" + "Turn: Player 3\n" + "Trump: ♣";
    assertEquals(trump.getCurrentPlayer(), 2);
    assertEquals(gameState, trump.getGameState());
    trump.play(2, 2);
    trump.play(3, 2);
    trump.play(4, 2);
    trump.play(5, 2);
    trump.play(0, 3);
    trump.play(1, 2);
    gameState = "Number of Players: 6\n" + "Player 1: A♣, 8♣, 2♣, 10♥, 4♥, J♠, 5♠\n"
      + "Player 2: K♣, 7♣, 8♦, 9♥, 3♥, 10♠, 4♠\n" + "Player 3: Q♣, 6♣, A♥, 8♥, 2♥, 9♠, 3♠\n"
      + "Player 4: J♣, 5♣, K♥, 7♥, A♠, 8♠, 2♠\n" + "Player 5: 10♣, 4♣, Q♥, 6♥, K♠, 7♠\n"
      + "Player 6: 9♣, 3♣, J♥, 5♥, Q♠, 6♠\n" + "Player 1: 0 hands won\n" + "Player 2: 1 hands won\n"
      + "Player 3: 1 hands won\n" + "Player 4: 0 hands won\n" + "Player 5: 0 hands won\n"
      + "Player 6: 0 hands won\n" + "Turn: Player 2\n" + "Trump: ♣";
    assertEquals(trump.getCurrentPlayer(), 1);
    assertEquals(gameState, trump.getGameState());
    trump.play(1, 2);
    trump.play(2, 1); // trump card
    trump.play(3, 2); // dump card (hearts)
    trump.play(4, 2); // dump card (hearts)
    trump.play(5, 2); // dump card (hearts)
    trump.play(0, 2); // trump card, lower rank than the earlier trump though
    gameState = "Number of Players: 6\n" + "Player 1: A♣, 8♣, 10♥, 4♥, J♠, 5♠\n"
      + "Player 2: K♣, 7♣, 9♥, 3♥, 10♠, 4♠\n" + "Player 3: Q♣, A♥, 8♥, 2♥, 9♠, 3♠\n"
      + "Player 4: J♣, 5♣, 7♥, A♠, 8♠, 2♠\n" + "Player 5: 10♣, 4♣, 6♥, K♠, 7♠\n"
      + "Player 6: 9♣, 3♣, 5♥, Q♠, 6♠\n" + "Player 1: 0 hands won\n" + "Player 2: 1 hands won\n"
      + "Player 3: 2 hands won\n" + "Player 4: 0 hands won\n" + "Player 5: 0 hands won\n"
      + "Player 6: 0 hands won\n" + "Turn: Player 3\n" + "Trump: ♣";
    assertEquals(trump.getCurrentPlayer(), 2);
    assertEquals(gameState, trump.getGameState());
    trump.play(2, 2);
    trump.play(3, 2);
    trump.play(4, 2);
    trump.play(5, 2);
    trump.play(0, 2);
    trump.play(1, 2);
    gameState = "Number of Players: 6\n" + "Player 1: A♣, 8♣, 4♥, J♠, 5♠\n"
      + "Player 2: K♣, 7♣, 3♥, 10♠, 4♠\n" + "Player 3: Q♣, A♥, 2♥, 9♠, 3♠\n"
      + "Player 4: J♣, 5♣, A♠, 8♠, 2♠\n" + "Player 5: 10♣, 4♣, K♠, 7♠\n"
      + "Player 6: 9♣, 3♣, Q♠, 6♠\n" + "Player 1: 1 hands won\n" + "Player 2: 1 hands won\n"
      + "Player 3: 2 hands won\n" + "Player 4: 0 hands won\n" + "Player 5: 0 hands won\n"
      + "Player 6: 0 hands won\n" + "Turn: Player 1\n" + "Trump: ♣";
    assertEquals(trump.getCurrentPlayer(), 0);
    assertEquals(gameState, trump.getGameState());
    trump.play(0, 2);
    trump.play(1, 2);
    trump.play(2, 1); // ace of hearts
    trump.play(3, 1); // 5 of clubs (trump)
    trump.play(4, 0); // 10 of clubs (trump, this will win)
    trump.play(5, 2); // dump card (spades)
    gameState = "Number of Players: 6\n" + "Player 1: A♣, 8♣, J♠, 5♠\n"
      + "Player 2: K♣, 7♣, 10♠, 4♠\n" + "Player 3: Q♣, 2♥, 9♠, 3♠\n" + "Player 4: J♣, A♠, 8♠, 2♠\n"
      + "Player 5: 4♣, K♠, 7♠\n" + "Player 6: 9♣, 3♣, 6♠\n" + "Player 1: 1 hands won\n"
      + "Player 2: 1 hands won\n" + "Player 3: 2 hands won\n" + "Player 4: 0 hands won\n"
      + "Player 5: 1 hands won\n" + "Player 6: 0 hands won\n" + "Turn: Player 5\n" + "Trump: ♣";
    assertEquals(trump.getCurrentPlayer(), 4);
    assertEquals(gameState, trump.getGameState());
  }

  @Test
  public void testGetGameState_ninePlayers() {
    CardGameModel<Card> trump = new WhistTrumpModel();
    trump.startPlay(9, trump.getDeck());
    String gameState = "Number of Players: 9\n" + "Player 1: A♣, 5♣, 9♦, K♥, 4♥, 8♠\n"
      + "Player 2: K♣, 4♣, 8♦, Q♥, 3♥, 7♠\n" + "Player 3: Q♣, 3♣, 7♦, J♥, 2♥, 6♠\n"
      + "Player 4: J♣, 2♣, 6♦, 10♥, A♠, 5♠\n" + "Player 5: 10♣, A♦, 5♦, 9♥, K♠, 4♠\n"
      + "Player 6: 9♣, K♦, 4♦, 8♥, Q♠, 3♠\n" + "Player 7: 8♣, Q♦, 3♦, 7♥, J♠, 2♠\n"
      + "Player 8: 7♣, J♦, 2♦, 6♥, 10♠\n" + "Player 9: 6♣, 10♦, A♥, 5♥, 9♠\n"
      + "Player 1: 0 hands won\n" + "Player 2: 0 hands won\n" + "Player 3: 0 hands won\n"
      + "Player 4: 0 hands won\n" + "Player 5: 0 hands won\n" + "Player 6: 0 hands won\n"
      + "Player 7: 0 hands won\n" + "Player 8: 0 hands won\n" + "Player 9: 0 hands won\n"
      + "Turn: Player 1\n" + "Trump: ♣";
    assertEquals(trump.getCurrentPlayer(), 0);
    assertEquals(gameState, trump.getGameState());
    trump.play(0, 2);
    trump.play(1, 2);
    trump.play(2, 2);
    trump.play(3, 2);
    trump.play(4, 2);
    trump.play(5, 2);
    trump.play(6, 2);
    trump.play(7, 2);
    trump.play(8, 1);
    gameState = "Number of Players: 9\n" + "Player 1: A♣, 5♣, K♥, 4♥, 8♠\n"
      + "Player 2: K♣, 4♣, Q♥, 3♥, 7♠\n" + "Player 3: Q♣, 3♣, J♥, 2♥, 6♠\n"
      + "Player 4: J♣, 2♣, 10♥, A♠, 5♠\n" + "Player 5: 10♣, A♦, 9♥, K♠, 4♠\n"
      + "Player 6: 9♣, K♦, 8♥, Q♠, 3♠\n" + "Player 7: 8♣, Q♦, 7♥, J♠, 2♠\n"
      + "Player 8: 7♣, J♦, 6♥, 10♠\n" + "Player 9: 6♣, A♥, 5♥, 9♠\n" + "Player 1: 0 hands won\n"
      + "Player 2: 0 hands won\n" + "Player 3: 0 hands won\n" + "Player 4: 0 hands won\n"
      + "Player 5: 0 hands won\n" + "Player 6: 0 hands won\n" + "Player 7: 0 hands won\n"
      + "Player 8: 0 hands won\n" + "Player 9: 1 hands won\n" + "Turn: Player 9\n" + "Trump: ♣";
    assertEquals(trump.getCurrentPlayer(), 8);
    assertEquals(gameState, trump.getGameState());
    trump.play(8, 3);
    trump.play(0, 4);
    trump.play(1, 4);
    trump.play(2, 4);
    trump.play(3, 4);
    trump.play(4, 3);
    trump.play(5, 3);
    trump.play(6, 3);
    trump.play(7, 3);
    gameState = "Number of Players: 9\n" + "Player 1: A♣, 5♣, K♥, 4♥\n"
      + "Player 2: K♣, 4♣, Q♥, 3♥\n" + "Player 3: Q♣, 3♣, J♥, 2♥\n" + "Player 4: J♣, 2♣, 10♥, A♠\n"
      + "Player 5: 10♣, A♦, 9♥, 4♠\n" + "Player 6: 9♣, K♦, 8♥, 3♠\n" + "Player 7: 8♣, Q♦, 7♥, 2♠\n"
      + "Player 8: 7♣, J♦, 6♥\n" + "Player 9: 6♣, A♥, 5♥\n" + "Player 1: 0 hands won\n"
      + "Player 2: 0 hands won\n" + "Player 3: 0 hands won\n" + "Player 4: 0 hands won\n"
      + "Player 5: 1 hands won\n" + "Player 6: 0 hands won\n" + "Player 7: 0 hands won\n"
      + "Player 8: 0 hands won\n" + "Player 9: 1 hands won\n" + "Turn: Player 5\n" + "Trump: ♣";
    assertEquals(trump.getCurrentPlayer(), 4);
    assertEquals(gameState, trump.getGameState());
    trump.play(4, 1); // diamonds is now the suit of this hand
    trump.play(5, 1);
    trump.play(6, 1);
    trump.play(7, 1);
    trump.play(8, 2); // no diamonds, throws away a heart
    trump.play(0, 0); // ace of clubs, highest trump card, this player will win
    trump.play(1, 1); // 4 of clubs, trump card but not higher than the other trump
    trump.play(2, 0); // jack of clubs, trump card but not higher than the other trump
    trump.play(3, 2); // no diamonds, throws away a heart
    gameState = "Number of Players: 9\n" + "Player 1: 5♣, K♥, 4♥\n" + "Player 2: K♣, Q♥, 3♥\n"
      + "Player 3: 3♣, J♥, 2♥\n" + "Player 4: J♣, 2♣, A♠\n" + "Player 5: 10♣, 9♥, 4♠\n"
      + "Player 6: 9♣, 8♥, 3♠\n" + "Player 7: 8♣, 7♥, 2♠\n" + "Player 8: 7♣, 6♥\n"
      + "Player 9: 6♣, A♥\n" + "Player 1: 1 hands won\n" + "Player 2: 0 hands won\n"
      + "Player 3: 0 hands won\n" + "Player 4: 0 hands won\n" + "Player 5: 1 hands won\n"
      + "Player 6: 0 hands won\n" + "Player 7: 0 hands won\n" + "Player 8: 0 hands won\n"
      + "Player 9: 1 hands won\n" + "Turn: Player 1\n" + "Trump: ♣";
    assertEquals(trump.getCurrentPlayer(), 0);
    assertEquals(gameState, trump.getGameState());
    trump.play(0, 2); // hearts is now the suit of this hand
    trump.play(1, 2);
    trump.play(2, 2);
    trump.play(3, 1); // 2 of clubs, trump card
    trump.play(4, 1);
    trump.play(5, 1);
    trump.play(6, 1);
    trump.play(7, 1);
    trump.play(8, 1);
    gameState = "Number of Players: 9\n" + "Player 1: 5♣, K♥\n" + "Player 2: K♣, Q♥\n"
      + "Player 3: 3♣, J♥\n" + "Player 4: J♣, A♠\n" + "Player 5: 10♣, 4♠\n" + "Player 6: 9♣, 3♠\n"
      + "Player 7: 8♣, 2♠\n" + "Player 8: 7♣\n" + "Player 9: 6♣\n" + "Player 1: 1 hands won\n"
      + "Player 2: 0 hands won\n" + "Player 3: 0 hands won\n" + "Player 4: 1 hands won\n"
      + "Player 5: 1 hands won\n" + "Player 6: 0 hands won\n" + "Player 7: 0 hands won\n"
      + "Player 8: 0 hands won\n" + "Player 9: 1 hands won\n" + "Turn: Player 4\n" + "Trump: ♣";
    assertEquals(trump.getCurrentPlayer(), 3);
    assertEquals(gameState, trump.getGameState());
    trump.play(3, 1); // spades is now the suit of this hand
    trump.play(4, 1);
    trump.play(5, 1);
    trump.play(6, 1);
    trump.play(7, 0); // 7 of clubs, trump card (out of cards now)
    trump.play(8, 0); // 6 of clubs, trump card (out of cards now)
    trump.play(0, 1); // no spades, throws away a heart
    trump.play(1, 1); // no spades, throws away a heart
    trump.play(2, 1); // no spades, throws away a heart
    // Player 8 (index 7) won this hand, but since he is out of cards, the next hand begins
    // with the first player with cards
    gameState = "Number of Players: 9\n" + "Player 1: 5♣\n" + "Player 2: K♣\n" + "Player 3: 3♣\n"
      + "Player 4: J♣\n" + "Player 5: 10♣\n" + "Player 6: 9♣\n" + "Player 7: 8♣\n" + "Player 8: \n"
      + "Player 9: \n" + "Player 1: 1 hands won\n" + "Player 2: 0 hands won\n"
      + "Player 3: 0 hands won\n" + "Player 4: 1 hands won\n" + "Player 5: 1 hands won\n"
      + "Player 6: 0 hands won\n" + "Player 7: 0 hands won\n" + "Player 8: 1 hands won\n"
      + "Player 9: 1 hands won\n" + "Turn: Player 1\n" + "Trump: ♣";
    assertEquals(trump.getCurrentPlayer(), 0);
    assertEquals(gameState, trump.getGameState());
    trump.play(0, 0); // clubs is now the suit of this hand
    trump.play(1, 0);
    trump.play(2, 0);
    trump.play(3, 0);
    trump.play(4, 0);
    trump.play(5, 0);
    trump.play(6, 0);
    // Six way tie, shows that getGameState works for multiple winners
    gameState = "Number of Players: 9\n" + "Player 1: \n" + "Player 2: \n" + "Player 3: \n"
      + "Player 4: \n" + "Player 5: \n" + "Player 6: \n" + "Player 7: \n" + "Player 8: \n"
      + "Player 9: \n" + "Player 1: 1 hands won\n" + "Player 2: 1 hands won\n"
      + "Player 3: 0 hands won\n" + "Player 4: 1 hands won\n" + "Player 5: 1 hands won\n"
      + "Player 6: 0 hands won\n" + "Player 7: 0 hands won\n" + "Player 8: 1 hands won\n"
      + "Player 9: 1 hands won\n"
      + "Game over. Player 1, Player 2, Player 4, Player 5, Player 8, Player 9 won.\n" + "Trump: ♣";
    assertEquals(trump.getCurrentPlayer(), -1); // at the end of the game, curPlayer is -1
    assertEquals(gameState, trump.getGameState());
  }
}
