package cs3500.hw02;

import java.util.Objects;

/**
 * Represents a playing card from a standard deck of cards.
 */
public class Card implements Comparable<Card> {
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
   * Returns a String of the Rank appended to the Suit
   * @return String of the Rank appended to the Suit
   */
  @Override
  public String toString() {
    return rankValue.toString() + suitValue.toString();
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

  /**
   * Compares this Card to the given Card. The order specified for this method is
   * suits take priority (Clubs, Diamonds, Hearts, Spades), followed by the rank value
   * (Ace, King, ..., 3, 2).
   *
   * @param that the Card being compared to this Card
   * @return -1 if this is less than the given Card, 0 if this is equal to the given Card, and
   * 1 if this is greater than the given Card.
   */
  @Override
  public int compareTo(Card that) {
    int thisSuitIdx = this.suitValue.ordinal();
    int thatSuitIdx = that.getSuit().ordinal();
    if (thisSuitIdx < thatSuitIdx) {
      return -1;
    } else if (thisSuitIdx > thatSuitIdx) {
      return 1;
    } else {
      int thisRankIdx = this.rankValue.ordinal();
      int thatRankIdx = that.getRank().ordinal();
      if (thisRankIdx < thatRankIdx) {
        return -1;
      } else if (thisRankIdx == thatRankIdx) {
        return 0;
      } else {
        return 1;
      }
    }
  }
}
