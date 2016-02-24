package cs3500.hw03;

import java.io.IOException;
import java.util.*;

/**
 * Controller for the IWhistController interface
 */
public class WhistController implements IWhistController {
  private final Readable input;
  private final Appendable output;

  /**
   * Constructor for the WhistController
   * @param in the input from the user
   * @param out the output of the game
   */
  public WhistController(Readable in, Appendable out) {
    input = in;
    output = out;
  }

  @Override
  public <K> void startGame(CardGameModel<K> game, int numPlayers) {
    Objects.requireNonNull(game);
    Scanner scan = new Scanner(this.input);
    try {
      List<K> deck = game.getDeck();
      game.startPlay(numPlayers, deck);
      String gameState = game.getGameState() + "\n";
      this.output.append(gameState);
    } catch (IOException io) {
      io.printStackTrace();
    }
    while(!game.isGameOver()) {
      if (scan.hasNextInt()) {
        try {
          game.play(game.getCurrentPlayer(), scan.nextInt());
          String gameState = game.getGameState() + "\n";
          this.output.append(gameState);
          scan.nextLine();
        } catch (IllegalArgumentException | InputMismatchException e) {
          String exceptionMessage = e.getMessage();
          String outputMessage = "Try again, that was invalid input: " + exceptionMessage + "\n";
          try {
            this.output.append(outputMessage);
          } catch (IOException io) {
            io.printStackTrace();
          }
        } catch (IOException io) {
          io.printStackTrace();
        }
      } else if (scan.hasNextLine()) {
        try {
          scan.nextLine();
          String outputMessage = "Try again, that was invalid input: need to input integers\n";
          this.output.append(outputMessage);
        } catch (IOException io) {
          io.printStackTrace();
        }
      }
    }
    scan.close();
  }
}
