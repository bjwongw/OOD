package cs3500.hw02;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player in a card game.
 */
public class Player {
  private List<Card> hand;
  private int wins;

  /**
   * Constructs a Player
   */
  public Player() {
    hand = new ArrayList<>();
    wins = 0;
  }

  /**
   * Returns the player's hand as a List of Cards
   *
   * @return the player's hand
   */
  public List<Card> getHand() {
    return hand;
  }

  /**
   * Inserts the given card into the player's hand at the end of the list
   *
   * @param givenCard the Card given to insert into the hand
   */
  public void addToHand(Card givenCard) {
    hand.add(givenCard);
  }

  /**
   * Returns the player's wins
   * @return the number of wins this player has
   */
  public int getWins() {
   return wins;
   }

  /**
   * Adds 1 to this player's wins field
   */
  public void addWin() {
    wins += 1;
  }
}
