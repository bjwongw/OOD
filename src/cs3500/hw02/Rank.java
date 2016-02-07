package cs3500.hw02;

import java.util.Arrays;
import java.util.List;

/**
 * Represents the rank values in a standard deck of playing cards.
 */
public enum Rank {
  Ace("A"), Two("2"), Three("3"), Four("4"), Five("5"), Six("6"), Seven("7"), Eight("8"),
  Nine("9"), Ten("10"), Jack("J"), Queen("Q"), King("K");

  private final String symbol;

  /**
   * Constructs a Rank
   *
   * @param symbol the graphical representation of the Rank
   */
  Rank(String symbol) {
    this.symbol = symbol;
  }

  /**
   * List of Ranks in order from Ace to Two (i.e. Ace, King, Queen, ..., 3, 2)
   */
  public static final List<Rank> ACE_HIGH_DESC = Arrays.asList(Ace, King, Queen, Jack, Ten,
    Nine, Eight, Seven, Six, Five, Four, Three, Two);

  /**
   * Returns the symbol of the Rank
   *
   * @return the symbol of the Rank
   */
  public String getRankSymbol() {
    return symbol;
  }
}
