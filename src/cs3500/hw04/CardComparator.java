package cs3500.hw04;

import cs3500.hw02.Card;
import java.util.Comparator;

/**
 * Comparator class to evaluate the ordering of Cards. The order specified
 * for this class is suits take priority (Clubs, Diamonds, Hearts, Spades), followed
 * by the rank value (Ace, King, ..., 3, 2).
 */
public class CardComparator implements Comparator<Card> {

  /**
   * Constructs a CardComparator
   */
  public CardComparator() { }

  @Override
  public int compare(Card first, Card second) {
    if (first.getSuit().compareTo(second.getSuit()) < 0) return -1;
    else if (first.getSuit().compareTo(second.getSuit()) < 0) return 1;
    else {
      if (first.getRank().compareTo(second.getRank()) < 0) return -1;
      else if (first.getRank().compareTo(second.getRank()) > 0) return 1;
      else return 0;
    }
  }
}
