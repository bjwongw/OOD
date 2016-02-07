package cs3500.hw02;

import java.util.List;

/**
 * Specifies operations for any generic card game model.
 */
public interface GenericCardGameModel<K> {

  /**
   * Gets the entire deck of relevant cards in a card game.
   *
   * @return the entire deck of relevant cards.
   */
  List<K> getDeck();

  /**
   * Distributes the cards in the specified order among the players in round-
   * robin fashion.
   *
   * @param numPlayers the number of players in the game
   * @param deck the deck of cards
   * @throws IllegalArgumentException if numPlayers is not greater than 1, or if
   * the deck passed to it is invalid (this is dependent on the implementation).
   */
  void startPlay(int numPlayers, List<K> deck);

  /**
   * Returns a String that contains the entire state of the game as follows,
   * one on each line:
   *
   * <ul>
   *   <li>Number of players: N (printed as a normal decimal number)</li>
   *   <li>Player 1: <i>cards in sorted order</i> (printed as a comma-separated list)</li>
   *   <li>...</li>
   *   <li>Player 2: <i>cards in sorted order</i> (printed as a comma-separated list)</li>
   *   <li>Player N: <i>cards in sorted order</i> (printed as a comma-separated list)</li>
   * </ul>
   *
   * @return a String that contains the entire state of the game
   */
  String getGameState();
}
