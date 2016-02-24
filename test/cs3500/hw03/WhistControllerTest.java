package cs3500.hw03;

import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import static org.junit.Assert.*;

/**
 * Tests for the WhistController
 */
public class WhistControllerTest {

  /**
   * An interaction with the user consists of some input to send the program
   * and some output to expect. We represent it as an object that takes in two
   * StringBuilders and produces the intended effects on them
   */
  interface Interaction {
    void apply(StringBuilder in, StringBuilder out);
  }

  /**
   * Represents the printing of a sequence of lines to output
   * @param lines the lines to print
   * @return appends the lines to go to output in the Interaction StringBuilder
   */
  static Interaction prints(String[] lines) {
    return (input, output) -> {
      for (String line : lines) {
        output.append(line).append("\n");
      }
    };
  }

  /**
   * Represents a user providing the program with an input
   * @param in the input
   * @return appends the input string to the Interaction StringBuilder
   */
  static Interaction inputs(String in) {
    return (input, output) -> input.append(in);
  }

  StringBuffer out = new StringBuffer();
  Reader in = new StringReader("");
  CardGameModel whist = new WhistModel();
  IWhistController controller = new WhistController(in, out);

  /**
   * Tester method to run mock input/output
   * @param model the CardGameModel
   * @param interactions the mock inputs and expected outputs
   * @param <K> type parameter (e.g. Card)
   */
  public <K> void testStartGame(CardGameModel<K> model, Interaction[] interactions) {
    StringBuilder fakeUserInput = new StringBuilder();
    StringBuilder expectedOutput = new StringBuilder();

    for (Interaction interaction : interactions) {
      interaction.apply(fakeUserInput, expectedOutput);
    }

    Readable input = new StringReader(fakeUserInput.toString());
    Appendable actualOutput = new StringBuilder();

    IWhistController controller = new WhistController(input, actualOutput);
    controller.startGame(model, 2);

    assertEquals(expectedOutput.toString(), actualOutput.toString());
  }

  /**
   * Tests the method startGame when given zero players
   */
  @Test(expected = IllegalArgumentException.class)
  public void startGameZeroPlayers() {
    out = new StringBuffer();
    in = new StringReader("");
    whist = new WhistModel();
    controller = new WhistController(in, out);
    controller.startGame(whist, 0);
  }

  /**
   * Tests the method startGame when given too few players
   */
  @Test(expected = IllegalArgumentException.class)
  public void startGameOnePlayer() {
    out = new StringBuffer();
    in = new StringReader("");
    whist = new WhistModel();
    controller = new WhistController(in, out);
    controller.startGame(whist, 1);
  }

  /**
   * Tests the method startGame and runs inputs all the way through the game.
   * Includes checks for invalid inputs
   */
  @Test
  public void testStartGame_twoPlayers() {
    Interaction[] interactions = new Interaction[] {
      prints(new String[] {"Number of Players: 2",
        "Player 1: A♣, Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥, 8♥, 6♥, "
          + "4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥, 7♥, 5♥, "
          + "3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠", "Player 1: 0 hands won", "Player 2: 0 hands won",
        "Turn: Player 1"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥, 8♥, 6♥, "
          + "4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥, 7♥, 5♥, "
          + "3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠", "Player 1: 0 hands won", "Player 2: 0 hands won",
        "Turn: Player 2"}),
      inputs("1\n"),
      prints(new String[] {"Number of Players: 2",
      "Player 1: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥, 8♥, 6♥, "
        + "4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠",
      "Player 2: K♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥, 7♥, 5♥, "
        + "3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠", "Player 1: 1 hands won", "Player 2: 0 hands won",
      "Turn: Player 1"}),
      inputs("6\n"),
      prints(new String[] {"Number of Players: 2",
      "Player 1: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥, 8♥, 6♥, "
        + "4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠",
      "Player 2: K♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥, 7♥, 5♥, "
        + "3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠", "Player 1: 1 hands won", "Player 2: 0 hands won",
      "Turn: Player 2"}),
      inputs("5\n"),
      prints(new String[] {"Number of Players: 2",
      "Player 1: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥, 8♥, 6♥, "
        + "4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠",
      "Player 2: K♣, 9♣, 7♣, 5♣, 3♣, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥, 7♥, 5♥, "
        + "3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠", "Player 1: 1 hands won", "Player 2: 1 hands won",
      "Turn: Player 2"}),
      inputs("-1\n"),
      prints(new String[] {"Try again, that was invalid input: The position -1 is invalid"}),
      inputs("100\n"),
      prints(new String[] {"Try again, that was invalid input: The position 100 is invalid"}),
      inputs("6\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥, 8♥, 6♥, "
          + "4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: K♣, 9♣, 7♣, 5♣, 3♣, Q♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥, 7♥, 5♥, "
          + "3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠", "Player 1: 1 hands won", "Player 2: 1 hands won",
        "Turn: Player 1"}),
      inputs("0\n"),
      prints(new String[] {"Try again, that was invalid input: Played a card of the " +
        "wrong suit, you have a card with the correct suit."}),
      inputs("6\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥, 8♥, 6♥, "
          + "4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: K♣, 9♣, 7♣, 5♣, 3♣, Q♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥, 7♥, 5♥, "
          + "3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠", "Player 1: 2 hands won", "Player 2: 1 hands won",
        "Turn: Player 1"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: 10♣, 8♣, 6♣, 4♣, 2♣, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥, 8♥, 6♥, "
          + "4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: K♣, 9♣, 7♣, 5♣, 3♣, Q♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥, 7♥, 5♥, "
          + "3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠", "Player 1: 2 hands won", "Player 2: 1 hands won",
        "Turn: Player 2"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: 10♣, 8♣, 6♣, 4♣, 2♣, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥, 8♥, 6♥, "
          + "4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: 9♣, 7♣, 5♣, 3♣, Q♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥, 7♥, 5♥, "
          + "3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠", "Player 1: 2 hands won", "Player 2: 2 hands won",
        "Turn: Player 2"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: 10♣, 8♣, 6♣, 4♣, 2♣, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥, 8♥, 6♥, "
          + "4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: 7♣, 5♣, 3♣, Q♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥, 7♥, 5♥, "
          + "3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠", "Player 1: 2 hands won", "Player 2: 2 hands won",
        "Turn: Player 1"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: 8♣, 6♣, 4♣, 2♣, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥, 8♥, 6♥, "
          + "4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: 7♣, 5♣, 3♣, Q♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥, 7♥, 5♥, "
          + "3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠", "Player 1: 3 hands won", "Player 2: 2 hands won",
        "Turn: Player 1"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: 6♣, 4♣, 2♣, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥, 8♥, 6♥, "
          + "4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: 7♣, 5♣, 3♣, Q♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥, 7♥, 5♥, "
          + "3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠", "Player 1: 3 hands won", "Player 2: 2 hands won",
        "Turn: Player 2"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: 6♣, 4♣, 2♣, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥, 8♥, 6♥, "
          + "4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: 5♣, 3♣, Q♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥, 7♥, 5♥, "
          + "3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠", "Player 1: 4 hands won", "Player 2: 2 hands won",
        "Turn: Player 1"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: 4♣, 2♣, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥, 8♥, 6♥, "
          + "4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: 5♣, 3♣, Q♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥, 7♥, 5♥, "
          + "3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠", "Player 1: 4 hands won", "Player 2: 2 hands won",
        "Turn: Player 2"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: 4♣, 2♣, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥, 8♥, 6♥, "
          + "4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: 3♣, Q♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥, 7♥, 5♥, "
          + "3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠", "Player 1: 5 hands won", "Player 2: 2 hands won",
        "Turn: Player 1"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: 2♣, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥, 8♥, 6♥, "
          + "4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: 3♣, Q♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥, 7♥, 5♥, "
          + "3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠", "Player 1: 5 hands won", "Player 2: 2 hands won",
        "Turn: Player 2"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: 2♣, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥, 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: Q♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠",
        "Player 1: 6 hands won", "Player 2: 2 hands won",
        "Turn: Player 1"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥, 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: Q♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠",
        "Player 1: 6 hands won", "Player 2: 2 hands won",
        "Turn: Player 2"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥, 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠",
        "Player 1: 7 hands won", "Player 2: 2 hands won",
        "Turn: Player 1"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: 7♦, 5♦, 3♦, A♥, Q♥, 10♥, 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠",
        "Player 1: 7 hands won", "Player 2: 2 hands won",
        "Turn: Player 2"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: 7♦, 5♦, 3♦, A♥, Q♥, 10♥, 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: 6♦, 4♦, 2♦, K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠",
        "Player 1: 8 hands won", "Player 2: 2 hands won",
        "Turn: Player 1"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: 5♦, 3♦, A♥, Q♥, 10♥, 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: 6♦, 4♦, 2♦, K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠",
        "Player 1: 8 hands won", "Player 2: 2 hands won",
        "Turn: Player 2"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: 5♦, 3♦, A♥, Q♥, 10♥, 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: 4♦, 2♦, K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠",
        "Player 1: 9 hands won", "Player 2: 2 hands won",
        "Turn: Player 1"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: 3♦, A♥, Q♥, 10♥, 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: 4♦, 2♦, K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠",
        "Player 1: 9 hands won", "Player 2: 2 hands won",
        "Turn: Player 2"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: 3♦, A♥, Q♥, 10♥, 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: 2♦, K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠",
        "Player 1: 10 hands won", "Player 2: 2 hands won",
        "Turn: Player 1"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: A♥, Q♥, 10♥, 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: 2♦, K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠",
        "Player 1: 10 hands won", "Player 2: 2 hands won",
        "Turn: Player 2"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: A♥, Q♥, 10♥, 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠",
        "Player 1: 11 hands won", "Player 2: 2 hands won",
        "Turn: Player 1"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠",
        "Player 1: 11 hands won", "Player 2: 2 hands won",
        "Turn: Player 2"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: J♥, 9♥, 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠",
        "Player 1: 12 hands won", "Player 2: 2 hands won",
        "Turn: Player 1"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: 10♥, 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: J♥, 9♥, 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠",
        "Player 1: 12 hands won", "Player 2: 2 hands won",
        "Turn: Player 2"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: 10♥, 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: 9♥, 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠",
        "Player 1: 13 hands won", "Player 2: 2 hands won",
        "Turn: Player 1"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: 9♥, 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠",
        "Player 1: 13 hands won", "Player 2: 2 hands won",
        "Turn: Player 2"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠",
        "Player 1: 14 hands won", "Player 2: 2 hands won",
        "Turn: Player 1"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠",
        "Player 1: 14 hands won", "Player 2: 2 hands won",
        "Turn: Player 2"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠",
        "Player 1: 15 hands won", "Player 2: 2 hands won",
        "Turn: Player 1"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠",
        "Player 1: 15 hands won", "Player 2: 2 hands won",
        "Turn: Player 2"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠",
        "Player 1: 16 hands won", "Player 2: 2 hands won",
        "Turn: Player 1"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠",
        "Player 1: 16 hands won", "Player 2: 2 hands won",
        "Turn: Player 2"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠",
        "Player 1: 17 hands won", "Player 2: 2 hands won",
        "Turn: Player 1"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: K♠, J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠",
        "Player 1: 17 hands won", "Player 2: 2 hands won",
        "Turn: Player 2"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: K♠, J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠",
        "Player 1: 18 hands won", "Player 2: 2 hands won",
        "Turn: Player 1"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠",
        "Player 1: 18 hands won", "Player 2: 2 hands won",
        "Turn: Player 2"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: J♠, 9♠, 7♠, 5♠, 3♠",
        "Player 2: 10♠, 8♠, 6♠, 4♠, 2♠",
        "Player 1: 19 hands won", "Player 2: 2 hands won",
        "Turn: Player 1"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: 9♠, 7♠, 5♠, 3♠",
        "Player 2: 10♠, 8♠, 6♠, 4♠, 2♠",
        "Player 1: 19 hands won", "Player 2: 2 hands won",
        "Turn: Player 2"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: 9♠, 7♠, 5♠, 3♠",
        "Player 2: 8♠, 6♠, 4♠, 2♠",
        "Player 1: 20 hands won", "Player 2: 2 hands won",
        "Turn: Player 1"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: 7♠, 5♠, 3♠",
        "Player 2: 8♠, 6♠, 4♠, 2♠",
        "Player 1: 20 hands won", "Player 2: 2 hands won",
        "Turn: Player 2"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: 7♠, 5♠, 3♠",
        "Player 2: 6♠, 4♠, 2♠",
        "Player 1: 21 hands won", "Player 2: 2 hands won",
        "Turn: Player 1"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: 5♠, 3♠",
        "Player 2: 6♠, 4♠, 2♠",
        "Player 1: 21 hands won", "Player 2: 2 hands won",
        "Turn: Player 2"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: 5♠, 3♠",
        "Player 2: 4♠, 2♠",
        "Player 1: 22 hands won", "Player 2: 2 hands won",
        "Turn: Player 1"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: 3♠",
        "Player 2: 4♠, 2♠",
        "Player 1: 22 hands won", "Player 2: 2 hands won",
        "Turn: Player 2"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: 3♠",
        "Player 2: 2♠",
        "Player 1: 23 hands won", "Player 2: 2 hands won",
        "Turn: Player 1"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: ",
        "Player 2: 2♠",
        "Player 1: 23 hands won", "Player 2: 2 hands won",
        "Turn: Player 2"}),
      inputs("0\n"),
      prints(new String[] {"Number of Players: 2",
        "Player 1: ",
        "Player 2: ",
        "Player 1: 24 hands won", "Player 2: 2 hands won",
        "Game over. Player 1 won."})
    };
    testStartGame(new WhistModel(), interactions);
  }

  /**
   * Tests the method startGame and runs inputs all the way through the game.
   */
  @Test
  public void testStartGame_fivePlayers() {
    StringBuilder fakeUserInput = new StringBuilder();
    StringBuilder expectedOutput = new StringBuilder();

    WhistModel testModel = new WhistModel();
    testModel.startPlay(5, testModel.getDeck());
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

    testModel.play(3, 1);
    fakeUserInput.append("1\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(4, 1);
    fakeUserInput.append("1\n");
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

    testModel.play(3, 1);
    fakeUserInput.append("1\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(4, 3);
    fakeUserInput.append("3\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(0, 4);
    fakeUserInput.append("4\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(3, 5);
    fakeUserInput.append("5\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(4, 3);
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

    testModel.play(2, 5);
    fakeUserInput.append("5\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(1, 5);
    fakeUserInput.append("5\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(2, 6);
    fakeUserInput.append("6\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(3, 5);
    fakeUserInput.append("5\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(4, 4);
    fakeUserInput.append("4\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(0, 6);
    fakeUserInput.append("6\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(4, 1);
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

    testModel.play(4, 3);
    fakeUserInput.append("3\n");
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

    testModel.play(2, 4);
    fakeUserInput.append("4\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(3, 4);
    fakeUserInput.append("4\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(2, 2);
    fakeUserInput.append("2\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(3, 2);
    fakeUserInput.append("2\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(4, 2);
    fakeUserInput.append("2\n");
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

    testModel.play(2, 2);
    fakeUserInput.append("2\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(3, 2);
    fakeUserInput.append("2\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(4, 2);
    fakeUserInput.append("2\n");
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

    testModel.play(0, 0);
    fakeUserInput.append("0\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(1, 1);
    fakeUserInput.append("1\n");
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

    testModel.play(2, 0);
    fakeUserInput.append("0\n");
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

    testModel.play(0, 0);
    fakeUserInput.append("0\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    testModel.play(1, 0);
    fakeUserInput.append("0\n");
    expectedOutput.append(testModel.getGameState());
    expectedOutput.append("\n");

    Readable input = new StringReader(fakeUserInput.toString());
    Appendable actualOutput = new StringBuilder();

    IWhistController controller = new WhistController(input, actualOutput);
    controller.startGame(new WhistModel(), 5);

    assertEquals(expectedOutput.toString(), actualOutput.toString());
  }
}
