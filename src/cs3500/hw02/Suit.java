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
   * List of Suits ordered alphabetically
   */
  public static final List<Suit> ABC_SUITS = Arrays.asList(Clubs, Diamonds, Hearts, Spades);

  /**
   * Returns the symbol of the Suit
   *
   * @return the symbol of the Suit
   */
  public String getSuitSymbol() {
    return symbol;
  }
}
