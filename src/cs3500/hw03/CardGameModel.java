package cs3500.hw03;

import cs3500.hw02.GenericCardGameModel;

/**
 * Specifies operations for any card game model.
 */
public interface CardGameModel<K> extends GenericCardGameModel<K> {

  /**
   * Plays the card at index cardIdx in the set of cards for player number playerNo.
   * It is assumed that both player numbers and card indices begin with 0. It is further
   * assumed that players' hands are sorted.
   *
   * @param playerNo the number of the Player
   * @param cardIdx the index of the Card
   */
  void play(int playerNo, int cardIdx);

  /**
   * Returns the number of the player whose turn it is to play.
   *
   * @return the number of the player whose turn it is to play.
   */
  int getCurrentPlayer();

  /**
   * Determines if the game is over or not.
   *
   * @return true if the game is over, false otherwise.
   */
  boolean isGameOver();
}
