package cs3500.hw03;

/**
 * Specifies operations for a WhistController.
 */
public interface IWhistController {

  /**
   * Starts the provided game with the provided number of players, and returns only after
   * the game has ended.
   *
   * @param game the type of game to play
   * @param numPlayers the number of Players to play in this game
   */
  <K> void startGame(CardGameModel<K> game, int numPlayers);
}
