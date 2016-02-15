package cs3500.hw03;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

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
      game.startPlay(numPlayers, game.getDeck());
      String gameState = game.getGameState() + "\n";
      this.output.append(gameState);
    } catch (IllegalArgumentException ex) {
      String exceptionMessage = ex.getMessage();
      String outputMessage = "Try again, that was invalid input: " + exceptionMessage + "\n";
      try {
        this.output.append(outputMessage);
        return;
      } catch (IOException io) {
        System.out.println("Try again, that was invalid input: input/output exception\n");
      }
    } catch (IOException io) {
      System.out.println("Try again, that was invalid input: input/output exception\n");
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
            System.out.println("Try again, that was invalid input: input/output exception\n");
          }
        } catch (IOException io) {
          System.out.println("Try again, that was invalid input: input/output exception\n");
        }
      } else if (scan.hasNextLine()) {
        try {
          scan.nextLine();
          String outputMessage = "Try again, that was invalid input: need to input integers\n";
          this.output.append(outputMessage);
        } catch (IOException io) {
          System.out.println("Try again, that was invalid input: input/output exception\n");
        }
      }
    }
    scan.close();
  }
}
