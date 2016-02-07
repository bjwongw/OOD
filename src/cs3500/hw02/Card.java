package cs3500.hw02;

import java.util.Objects;

/**
 * Represents a playing card from a standard deck of cards.
 */
public class Card {
  private final Rank rankValue;
  private final Suit suitValue;

  /**
   * Constructs a Card
   *
   * @param newRank the rank of the playing card
   * @param newSuit the suit of the playing card (clubs, diamonds, hearts, or spades)
   */
  public Card(Rank newRank, Suit newSuit) {
    this.rankValue = newRank;
    this.suitValue = newSuit;
  }

  /**
   * Constructs a Card with the same values as the given Card
   * @param card the Card whose values will be copied
   */
  public Card(Card card) {
    rankValue = card.getRank();
    suitValue = card.getSuit();
  }

  /**
   * Returns the Rank associated with this Card
   *
   * @return the rankValue of this Card
   */
  public Rank getRank() {
    return rankValue;
  }

  /**
   * Returns the Suit associated with this Card
   *
   * @return the suitValue of this Card
   */
  public Suit getSuit() {
    return suitValue;
  }

  /**
   * Returns this Card's value and suit appended together in String form.
   *
   * @return a String of this Card's rank value concatenated to its suit.
   */
  public String printCard() {
    return rankValue.getRankSymbol() + suitValue.getSuitSymbol();
  }

  /**
   * The given object is equal to this Card if it is a Card with the same Rank and Suit
   * values.
   *
   * @param obj the given Object
   * @return true if this Card is the same as the given Object argument, false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (! (obj instanceof Card)) { return false; }
    Card that = (Card) obj;
    return this.rankValue == that.rankValue && this.suitValue == that.suitValue;
  }

  /**
   * Returns a hash code value for this Card.
   *
   * @return a hash code value for this Card.
   */
  @Override
  public int hashCode() {
    return Objects.hash(rankValue, suitValue);
  }
}
