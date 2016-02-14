package cs3500.hw02;

import java.util.Arrays;
import java.util.List;

/**
 * Represents the suit values in a standard deck of playing cards.
 */
public enum Suit {
  Clubs("♣"), Diamonds("♦"), Hearts("♥"), Spades("♠");

  private final String symbol;

  /**
   * Constructs a Suit
   *
   * @param symbol the graphical representation of the Suit
   */
  Suit(String symbol) {
    this.symbol = symbol;
  }

  /**
   * Returns the symbol of the Suit
   * @return Returns the symbol of the Suit
   */
  @Override
  public String toString() {
    return symbol;
  }
}
