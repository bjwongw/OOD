package cs3500.hw02;

/**
 * Represents the rank values in a standard deck of playing cards.
 */
public enum Rank {
  Ace("A"), King("K"), Queen("Q"), Jack("J"), Ten("10"), Nine("9"), Eight("8"), Seven("7"),
  Six("6"), Five("5"), Four("4"), Three("3"), Two("2");

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
   * Returns the symbol of this Rank
   * @return the symbol of this Rank
   */
  @Override
  public String toString() {
    return symbol;
  }
}
