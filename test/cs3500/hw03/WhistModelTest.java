package cs3500.hw03;

import cs3500.hw02.Card;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the WhistModel class
 */
public class WhistModelTest {

  String newline = System.getProperty("line.separator");

  /**
   * Tests for the method play, makes sure the card is removed from the player's hand
   */
  @Test
  public void testPlay_cardRemoved() {
    CardGameModel<Card> whist = new WhistModel();
    whist.startPlay(2, whist.getDeck());
    whist.play(0, 5);
    assertEquals(whist.getGameState(), "Number of Players: 2" + newline +
      "Player 1: A♣, Q♣, 10♣, 8♣, 6♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥, 8♥, 6♥, " +
      "4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠" + newline +
      "Player 2: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥, 7♥, 5♥, " +
      "3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠" + newline + "Player 1: 0 hands won" +
      newline + "Player 2: 0 hands won" + newline + "Turn: Player 2");
    whist.play(1, 0);
    assertEquals(whist.getGameState(), "Number of Players: 2" + newline +
      "Player 1: A♣, Q♣, 10♣, 8♣, 6♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥, 8♥, 6♥, " +
      "4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠" + newline +
      "Player 2: J♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥, 7♥, 5♥, " +
      "3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠" + newline + "Player 1: 0 hands won" +
      newline + "Player 2: 1 hands won" + newline + "Turn: Player 2");
  }

  /**
   * Tests for the method play, makes sure the current player index is advanced
   */
  @Test
  public void testPlay_advancesCurPlayer() {
    CardGameModel<Card> whist = new WhistModel();
    whist.startPlay(3, whist.getDeck());
    assertEquals(whist.getCurrentPlayer(), 0);
    whist.play(0, 3);
    assertEquals(whist.getCurrentPlayer(), 1);
    whist.play(1, 2);
    assertEquals(whist.getCurrentPlayer(), 2);
    whist.play(2, 1); // player three won
    assertEquals(whist.getCurrentPlayer(), 2);
    whist.play(2, 0);
    assertEquals(whist.getCurrentPlayer(), 0);
    whist.play(0, 1);
    assertEquals(whist.getCurrentPlayer(), 1);
    whist.play(1, 0); // player two won
    assertEquals(whist.getCurrentPlayer(), 1);
  }

  /**
   * Tests for the method play, makes sure that the winner of the hand gets their wins incremented
   */
  @Test
  public void testPlay_addsWins() {
    CardGameModel<Card> whist = new WhistModel();
    whist.startPlay(4, whist.getDeck());
    whist.play(0, 2);
    whist.play(1, 2);
    whist.play(2, 2);
    whist.play(3, 1); // player four wins the hand
    assertEquals(whist.getGameState(), "Number of Players: 4" + newline +
      "Player 1: A♣, 10♣, 2♣, J♦, 7♦, 3♦, Q♥, 8♥, 4♥, K♠, 9♠, 5♠" + newline +
      "Player 2: K♣, 9♣, A♦, 10♦, 6♦, 2♦, J♥, 7♥, 3♥, Q♠, 8♠, 4♠" + newline +
      "Player 3: Q♣, 8♣, K♦, 9♦, 5♦, A♥, 10♥, 6♥, 2♥, J♠, 7♠, 3♠" + newline +
      "Player 4: J♣, 3♣, Q♦, 8♦, 4♦, K♥, 9♥, 5♥, A♠, 10♠, 6♠, 2♠" +
      newline + "Player 1: 0 hands won" + newline + "Player 2: 0 hands won" + newline +
      "Player 3: 0 hands won" + newline + "Player 4: 1 hands won" + newline +
      "Turn: Player 4");
    whist.play(3, 3);
    whist.play(0, 3); // player one wins the hand
    whist.play(1, 4);
    whist.play(2, 4);
    assertEquals(whist.getGameState(), "Number of Players: 4" + newline +
      "Player 1: A♣, 10♣, 2♣, 7♦, 3♦, Q♥, 8♥, 4♥, K♠, 9♠, 5♠" + newline +
      "Player 2: K♣, 9♣, A♦, 10♦, 2♦, J♥, 7♥, 3♥, Q♠, 8♠, 4♠" + newline +
      "Player 3: Q♣, 8♣, K♦, 9♦, A♥, 10♥, 6♥, 2♥, J♠, 7♠, 3♠" + newline +
      "Player 4: J♣, 3♣, Q♦, 4♦, K♥, 9♥, 5♥, A♠, 10♠, 6♠, 2♠" +
      newline + "Player 1: 1 hands won" + newline + "Player 2: 0 hands won" + newline +
      "Player 3: 0 hands won" + newline + "Player 4: 1 hands won" + newline +
      "Turn: Player 1");
  }

  /**
   * Tests the method play, making sure an exception is thrown when the playerNo given is
   * not the current player
   */
  @Test(expected = IllegalArgumentException.class)
  public void playWrongPlayerIndex() {
    CardGameModel<Card> whist = new WhistModel();
    whist.startPlay(2, whist.getDeck());
    whist.play(2, 0);
  }

  /**
   * Tests the method play, making sure an exception is thrown when the position cardIdx is
   * greater than the highest index in the player's hand
   */
  @Test(expected = IllegalArgumentException.class)
  public void playInvalidCardIndex_highIndex() {
    CardGameModel<Card> whist = new WhistModel();
    whist.startPlay(4, whist.getDeck());
    whist.play(0, 20);
  }

  /**
   * Tests the method play, making sure an exception is thrown when the position cardIdx is
   * less than 0
   */
  @Test(expected = IllegalArgumentException.class)
  public void playInvalidCardIndex_negativeIndex() {
    CardGameModel<Card> whist = new WhistModel();
    whist.startPlay(4, whist.getDeck());
    whist.play(0, -2);
  }

  /**
   * Tests the method play, making sure an exception is thrown when the current card being played
   * is of the wrong suit, even though the player has a card of the correct suit
   */
  @Test(expected = IllegalArgumentException.class)
  public void playWrongSuit() {
    CardGameModel<Card> whist = new WhistModel();
    whist.startPlay(13, whist.getDeck());
    whist.play(0, 0); // first player plays Ace of Clubs
    whist.play(1, 2); // second player plays King of Hearts (even though he has King of Clubs)
  }

  /**
   * Tests for the method getCurrentPlayer
   */
  @Test
  public void testGetCurrentPlayer() {
    CardGameModel<Card> whist = new WhistModel();
    assertEquals(whist.getCurrentPlayer(), 0); // default should be 0

    List<Card> card = whist.getDeck();
    whist.startPlay(2, card);
    assertEquals(whist.getCurrentPlayer(), 0);
    whist.play(0, 0); // plays Player 1's first card, changing currentPlayer
    assertEquals(whist.getCurrentPlayer(), 1);
    whist.play(1, 0); // play Player 2's first card, ending the round
    assertEquals(whist.getCurrentPlayer(), 0); // player 1 won that round, so currentPlayer is 0

    whist.startPlay(3, card);
    assertEquals(whist.getCurrentPlayer(), 0);
    whist.play(0, 2);
    assertEquals(whist.getCurrentPlayer(), 1);
    whist.play(1, 1);
    assertEquals(whist.getCurrentPlayer(), 2);
    whist.play(2, 0); // player 3 plays the highest card, so the currentPlayer will be 2
    assertEquals(whist.getCurrentPlayer(), 2);
  }

  /**
   * Tests for the method isGameOver
   */
  @Test
  public void testIsGameOver() {
    CardGameModel<Card> whist = new WhistModel();
    assertEquals(whist.isGameOver(), false);

    whist.startPlay(5, whist.getDeck());
    assertEquals(whist.isGameOver(), false);
    for (int i = 0; i < 52; i++) {
      whist.play((i % 5), 0);
    }
    assertEquals(whist.isGameOver(), true);
  }

  /**
   * Tests for the method getGameState when there are no players.
   */
  @Test
  public void testGetGameState_noPlayers() {
    CardGameModel<Card> whist = new WhistModel();
    assertEquals(whist.getCurrentPlayer(), 0);
    assertEquals(whist.getGameState(), "Number of Players: 0" + newline +
      "Turn: Player 1");
  }

  /**
   * Tests for the method getGameState when there are two players.
   */
  @Test
  public void testGetGameState_twoPlayers() {
    CardGameModel<Card> whist = new WhistModel();
    whist.startPlay(2, whist.getDeck());
    assertEquals(whist.getCurrentPlayer(), 0);
    assertEquals(whist.getGameState(), "Number of Players: 2" + newline +
      "Player 1: A♣, Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥, 8♥, 6♥, " +
      "4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠" + newline +
      "Player 2: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥, 7♥, 5♥, " +
      "3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠" + newline + "Player 1: 0 hands won" +
      newline + "Player 2: 0 hands won" + newline + "Turn: Player 1");
    whist.play(0, 0);
    assertEquals(whist.getCurrentPlayer(), 1);
    assertEquals(whist.getGameState(), "Number of Players: 2" + newline +
      "Player 1: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥, 8♥, 6♥, " +
      "4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠" + newline +
      "Player 2: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥, 7♥, 5♥, " +
      "3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠" + newline + "Player 1: 0 hands won" +
      newline + "Player 2: 0 hands won" + newline + "Turn: Player 2");
    whist.play(1, 2);
    assertEquals(whist.getCurrentPlayer(), 0);
    assertEquals(whist.getGameState(), "Number of Players: 2" + newline +
      "Player 1: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥, 8♥, 6♥, " +
      "4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠" + newline +
      "Player 2: K♣, J♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥, 7♥, 5♥, " +
      "3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠" + newline + "Player 1: 1 hands won" +
      newline + "Player 2: 0 hands won" + newline + "Turn: Player 1");
  }

  /**
   * Tests for the method getGameState when there are thirteen players.
   */
  @Test
  public void testGetGameState_thirteenPlayers() {
    CardGameModel<Card> whist = new WhistModel();
    whist.startPlay(13, whist.getDeck());
    assertEquals(whist.getCurrentPlayer(), 0);
    assertEquals(whist.getGameState(), "Number of Players: 13" + newline +
      "Player 1: A♣, A♦, A♥, A♠" + newline + "Player 2: K♣, K♦, K♥, K♠" + newline +
      "Player 3: Q♣, Q♦, Q♥, Q♠" + newline + "Player 4: J♣, J♦, J♥, J♠" + newline +
      "Player 5: 10♣, 10♦, 10♥, 10♠" + newline + "Player 6: 9♣, 9♦, 9♥, 9♠" + newline +
      "Player 7: 8♣, 8♦, 8♥, 8♠" + newline + "Player 8: 7♣, 7♦, 7♥, 7♠" + newline +
      "Player 9: 6♣, 6♦, 6♥, 6♠" + newline + "Player 10: 5♣, 5♦, 5♥, 5♠" + newline +
      "Player 11: 4♣, 4♦, 4♥, 4♠" + newline + "Player 12: 3♣, 3♦, 3♥, 3♠" + newline +
      "Player 13: 2♣, 2♦, 2♥, 2♠" + newline + "Player 1: 0 hands won" + newline +
      "Player 2: 0 hands won" + newline + "Player 3: 0 hands won" + newline +
      "Player 4: 0 hands won" + newline + "Player 5: 0 hands won" + newline +
      "Player 6: 0 hands won" + newline + "Player 7: 0 hands won" + newline +
      "Player 8: 0 hands won" + newline + "Player 9: 0 hands won" + newline +
      "Player 10: 0 hands won" + newline + "Player 11: 0 hands won" + newline +
      "Player 12: 0 hands won" + newline + "Player 13: 0 hands won" + newline + "Turn: Player 1");
    whist.play(0, 3);
    whist.play(1, 3);
    whist.play(2, 3);
    whist.play(3, 3);
    whist.play(4, 3);
    whist.play(5, 3);
    assertEquals(whist.getCurrentPlayer(), 6);
    assertEquals(whist.getGameState(), "Number of Players: 13" + newline +
      "Player 1: A♣, A♦, A♥" + newline + "Player 2: K♣, K♦, K♥" + newline +
      "Player 3: Q♣, Q♦, Q♥" + newline + "Player 4: J♣, J♦, J♥" + newline +
      "Player 5: 10♣, 10♦, 10♥" + newline + "Player 6: 9♣, 9♦, 9♥" + newline +
      "Player 7: 8♣, 8♦, 8♥, 8♠" + newline + "Player 8: 7♣, 7♦, 7♥, 7♠" + newline +
      "Player 9: 6♣, 6♦, 6♥, 6♠" + newline + "Player 10: 5♣, 5♦, 5♥, 5♠" + newline +
      "Player 11: 4♣, 4♦, 4♥, 4♠" + newline + "Player 12: 3♣, 3♦, 3♥, 3♠" + newline +
      "Player 13: 2♣, 2♦, 2♥, 2♠" + newline + "Player 1: 0 hands won" + newline +
      "Player 2: 0 hands won" + newline + "Player 3: 0 hands won" + newline +
      "Player 4: 0 hands won" + newline + "Player 5: 0 hands won" + newline +
      "Player 6: 0 hands won" + newline + "Player 7: 0 hands won" + newline +
      "Player 8: 0 hands won" + newline + "Player 9: 0 hands won" + newline +
      "Player 10: 0 hands won" + newline + "Player 11: 0 hands won" + newline +
      "Player 12: 0 hands won" + newline + "Player 13: 0 hands won" + newline + "Turn: Player 7");
  }

  /**
   * Tests the method getGameState, checks the special message at the end for the winner
   */
  @Test
  public void testGetGameState_firstPlayerWins() {
    CardGameModel<Card> whist = new WhistModel();
    whist.startPlay(4, whist.getDeck());
    assertEquals(whist.isGameOver(), false);
    for (int i = 0; i < 52; i++) {
      whist.play((i % 4), 0);
    }
    assertEquals(whist.getGameState(), "Number of Players: 4" + newline +
    "Player 1: " + newline + "Player 2: " + newline + "Player 3: " + newline + "Player 4: " +
    newline + "Player 1: 13 hands won" + newline + "Player 2: 0 hands won" + newline +
    "Player 3: 0 hands won" + newline + "Player 4: 0 hands won" + newline +
      "Game over. Player 1 won.");
    assertEquals(whist.isGameOver(), true);
  }

  /**
   * Tests the method getGameState, checks the special message at the end for the winner
   */
  @Test
  public void testGetGameState_secondPlayerWins() {
    CardGameModel<Card> whist = new WhistModel();
    whist.startPlay(2, whist.getDeck());
    assertEquals(whist.isGameOver(), false);
    whist.play(0, 1); // first player does not play highest card
    whist.play(1, 0); // second player plays and wins
    for (int i = 1; i < 50; i++) {
      if ((i % 2) == 0) {
        whist.play((i % 2), 1);
      } else {
        whist.play((i % 2), 0);
      }
    }
    whist.play(0, 0); // first player plays the last card
    assertEquals(whist.getGameState(), "Number of Players: 2" + newline +
      "Player 1: " + newline + "Player 2: " + newline + "Player 1: 0 hands won" + newline +
      "Player 2: 26 hands won" + newline + "Game over. Player 2 won.");
    assertEquals(whist.isGameOver(), true);
  }
}
