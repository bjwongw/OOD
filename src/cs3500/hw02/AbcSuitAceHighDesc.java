package cs3500.hw02;

import java.util.Comparator;

/**
 * Comparator class to evaluate the ordering of Cards. The order specified
 * for this class is suits take priority (Clubs, Diamonds, Hearts, Spades), followed
 * by the rank value (Ace, King, ..., 3, 2).
 */
public class AbcSuitAceHighDesc implements Comparator<Card> {

  /**
   * Constructs an AbcSuitAceHighDesc
   */
  public AbcSuitAceHighDesc() { }

  @Override
  public int compare(Card first, Card second) {
    int firstSuitIndex = Suit.ABC_SUITS.indexOf(first.getSuit());
    int secondSuitIndex = Suit.ABC_SUITS.indexOf(second.getSuit());
    if (firstSuitIndex < secondSuitIndex) {
      return -1;
    } else if (firstSuitIndex > secondSuitIndex) {
      return 1;
    } else {
      int firstRankIndex = Rank.ACE_HIGH_DESC.indexOf(first.getRank());
      int secondRankIndex = Rank.ACE_HIGH_DESC.indexOf(second.getRank());
      if (firstRankIndex < secondRankIndex) {
        return -1;
      } else if (firstRankIndex == secondRankIndex) {
        return 0;
      } else {
        return 1;
      }
    }
  }
}
