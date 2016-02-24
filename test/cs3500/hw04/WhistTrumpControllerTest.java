package cs3500.hw04;

import cs3500.hw02.Card;
import cs3500.hw03.CardGameModel;
import cs3500.hw03.IWhistController;
import cs3500.hw03.WhistController;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the WhistController when playing with a WhistTrumpModel
 */
public class WhistTrumpControllerTest {

  StringBuffer out = new StringBuffer();
  Reader in = new StringReader("");
  CardGameModel trump = new WhistTrumpModel();
  IWhistController controller = new WhistController(in, out);

  /**
   * Tests the method startGame when given zero players
   */
  @Test(expected = IllegalArgumentException.class)
  public void startGameZeroPlayers() {
    out = new StringBuffer();
    in = new StringReader("");
    trump = new WhistTrumpModel();
    controller = new WhistController(in, out);
    controller.startGame(trump, 0);
  }

  /**
   * Tests the method startGame when given too few players
   */
  @Test(expected = IllegalArgumentException.class)
  public void startGameOnePlayer() {
    out = new StringBuffer();
    in = new StringReader("");
    trump = new WhistTrumpModel();
    controller = new WhistController(in, out);
    controller.startGame(trump, 1);
  }

  /**
   * Tests the method startGame using three players. Contains checks for invalid input
   * and trump card situations.
   */
  @Test
  public void startGameThreePlayersWithInvalidPlays() {
    StringBuilder fakeUserInput = new StringBuilder();
    StringBuilder expectedOutput = new StringBuilder();

    CardGameModel<Card> testModel = new WhistTrumpModel();
    testModel.startPlay(3, testModel.getDeck());
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    fakeUserInput.append("abc\n");
    expectedOutput.append("Try again, that was invalid input: need to input integers\n");

    fakeUserInput.append("3d\n");
    expectedOutput.append("Try again, that was invalid input: need to input integers\n");

    fakeUserInput.append("\nneanderthal\n");
    expectedOutput.append("Try again, that was invalid input: need to input integers\n");
    expectedOutput.append("Try again, that was invalid input: need to input integers\n");

    testModel.play(0, 12);
    fakeUserInput.append("12\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(1, 12);
    fakeUserInput.append("12\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    fakeUserInput.append("7\n");
    expectedOutput.append("Try again, that was invalid input: Played a card of the wrong suit, "
      + "you have a card with the correct suit.\n");

    testModel.play(2, 8);
    fakeUserInput.append("8\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    fakeUserInput.append("100\n");
    expectedOutput.append("Try again, that was invalid input: The position 100 is invalid\n");

    testModel.play(2, 5);
    fakeUserInput.append("5\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(0, 6);
    fakeUserInput.append("6\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(1, 4);
    fakeUserInput.append("4\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(1, 14);
    fakeUserInput.append("14\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(2, 14);
    fakeUserInput.append("14\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(0, 14);
    fakeUserInput.append("14\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(0, 4);
    fakeUserInput.append("4\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(1, 0);
    fakeUserInput.append("0\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(2, 2);
    fakeUserInput.append("2\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(1, 6);
    fakeUserInput.append("6\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(2, 4);
    fakeUserInput.append("4\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(0, 6);
    fakeUserInput.append("6\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(2, 3);
    fakeUserInput.append("3\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(0, 5);
    fakeUserInput.append("5\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(1, 3);
    fakeUserInput.append("3\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(2, 3);
    fakeUserInput.append("3\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(0, 4);
    fakeUserInput.append("4\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(1, 3);
    fakeUserInput.append("3\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(0, 8);
    fakeUserInput.append("8\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(1, 7);
    fakeUserInput.append("7\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(2, 9);
    fakeUserInput.append("9\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(1, 3);
    fakeUserInput.append("3\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(2, 6); // uses a dump card (hearts)
    fakeUserInput.append("6\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(0, 3); // uses a trump card (clubs), wins hand
    fakeUserInput.append("3\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(0, 6);
    fakeUserInput.append("6\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(1, 6);
    fakeUserInput.append("6\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(2, 6);
    fakeUserInput.append("6\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(0, 3);
    fakeUserInput.append("3\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(1, 5);
    fakeUserInput.append("5\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(2, 4);
    fakeUserInput.append("4\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(0, 5);
    fakeUserInput.append("5\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(1, 5);
    fakeUserInput.append("5\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(2, 5);
    fakeUserInput.append("5\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(2, 4);
    fakeUserInput.append("4\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(0, 4);
    fakeUserInput.append("4\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(1, 4);
    fakeUserInput.append("4\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(1, 2);
    fakeUserInput.append("2\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(2, 2);
    fakeUserInput.append("2\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(0, 0);
    fakeUserInput.append("0\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(0, 3);
    fakeUserInput.append("3\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(1, 1); // trump card (7 of clubs)
    fakeUserInput.append("1\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(2, 0); // trump card (queen of clubs), this player wins the hand
    fakeUserInput.append("0\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(2, 1);
    fakeUserInput.append("1\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(0, 2);
    fakeUserInput.append("2\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(1, 1);
    fakeUserInput.append("1\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(1, 0);
    fakeUserInput.append("0\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(2, 0);
    fakeUserInput.append("0\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(0, 0);
    fakeUserInput.append("0\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    Readable input = new StringReader(fakeUserInput.toString());
    Appendable actualOutput = new StringBuilder();

    IWhistController controller = new WhistController(input, actualOutput);
    controller.startGame(new WhistTrumpModel(), 3);

    assertEquals(expectedOutput.toString(), actualOutput.toString());
  }

  /**
   * Tests the method startGame using seven players.
   */
  @Test
  public void testStartGameSevenPlayers() {
    StringBuilder fakeUserInput = new StringBuilder();
    StringBuilder expectedOutput = new StringBuilder();

    CardGameModel<Card> testModel = new WhistTrumpModel();
    testModel.startPlay(7, testModel.getDeck());
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(0, 7);
    fakeUserInput.append("7\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(1, 6);
    fakeUserInput.append("6\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(2, 7);
    fakeUserInput.append("7\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(3, 6);
    fakeUserInput.append("6\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(4, 6);
    fakeUserInput.append("6\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(5, 5);
    fakeUserInput.append("5\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(6, 6);
    fakeUserInput.append("6\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(5, 5); // spades is now the suit of this hand
    fakeUserInput.append("5\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(6, 5);
    fakeUserInput.append("5\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(0, 6);
    fakeUserInput.append("6\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(1, 6);
    fakeUserInput.append("6\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(2, 6);
    fakeUserInput.append("6\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(3, 1); // 4 of clubs, trump card
    fakeUserInput.append("1\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    fakeUserInput.append("0\n");
    expectedOutput.append("Try again, that was invalid input: Played a card of the wrong suit,"
      + " you have a card with the correct suit.\n"); // can't play a trump card if you have a
                                                      // card of the correct suit

    testModel.play(4, 5);
    fakeUserInput.append("5\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(3, 2);
    fakeUserInput.append("2\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(4, 3);
    fakeUserInput.append("3\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(5, 2);
    fakeUserInput.append("2\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(6, 1);
    fakeUserInput.append("1\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(0, 3);
    fakeUserInput.append("3\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(1, 2);
    fakeUserInput.append("2\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(2, 3);
    fakeUserInput.append("3\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(6, 1); /// diamonds is now the suit of this hand
    fakeUserInput.append("1\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(0, 2);
    fakeUserInput.append("2\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(1, 2);
    fakeUserInput.append("2\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(2, 2);
    fakeUserInput.append("2\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(3, 1);
    fakeUserInput.append("1\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(4, 2);
    fakeUserInput.append("2\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(5, 0); // nine of clubs, trump card
    fakeUserInput.append("0\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(5, 0); // clubs is now the suit of this hand
    fakeUserInput.append("0\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(6, 0);
    fakeUserInput.append("0\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(0, 1);
    fakeUserInput.append("1\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(1, 1);
    fakeUserInput.append("1\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(2, 1);
    fakeUserInput.append("1\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(3, 0);
    fakeUserInput.append("0\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(4, 0);
    fakeUserInput.append("0\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(3, 0); // hearts is now the suit of this hand
    fakeUserInput.append("0\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(4, 1);
    fakeUserInput.append("1\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(5, 0);
    fakeUserInput.append("0\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(6, 1);
    fakeUserInput.append("1\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(0, 2);
    fakeUserInput.append("2\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(1, 1);
    fakeUserInput.append("1\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(2, 2);
    fakeUserInput.append("2\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(5, 0); // out of cards now, hearts is now the suit of this hand
    fakeUserInput.append("0\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(6, 0); // out of cards now
    fakeUserInput.append("0\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(0, 1);
    fakeUserInput.append("1\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(1, 1);
    fakeUserInput.append("1\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(2, 1);
    fakeUserInput.append("1\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(3, 0); // out of cards now
    fakeUserInput.append("0\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(4, 0); // 3 of clubs, trump card
    fakeUserInput.append("0\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(0, 0); // Player 5 (index 4) won the hand, but has no more cards. Players
                          // 6 and 7 are also out of cards, so play starts with Player 1
    fakeUserInput.append("0\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(1, 0);
    fakeUserInput.append("0\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(2, 0);
    fakeUserInput.append("0\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    Readable input = new StringReader(fakeUserInput.toString());
    Appendable actualOutput = new StringBuilder();

    IWhistController controller = new WhistController(input, actualOutput);
    controller.startGame(new WhistTrumpModel(), 7);

    assertEquals(expectedOutput.toString(), actualOutput.toString());
  }
}

