package cs3500.hw03;

import cs3500.hw02.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Specifies any operations for a Whist card game.
 *
 * <p>Whist is a trick-based card game that can be played by two or more players. The game uses a
 * standard 52-card deck. The objective of the game is for each player to win as many hands as
 * possible. The game ends when all the players have run out of cards, and the player with the
 * maximum number of hands wins the game.</p>
 *
 * <p>One of the players (the dealer) distributes the cards in a round-robin fashion to all the
 * players. Play then starts with the player sitting next to the dealer (in the dealing order).
 * The player who starts a hand is free to play any card. This card determines the suit of the
 * current hand. Each player in order must play a card of the same suit with the objective of
 * winning the hand. If a player does not have a card of the same suit, he or she “discards” the
 * hand by playing any other card. When the last player (the one sitting just before the one who
 * started the hand) has played the winner of the hand is determined. The winner of a hand is the
 * player who played the card of the correct suit and the highest value. The winner collects the
 * cards in the hand and keeps them aside, and then starts the next hand.</p>
 */
public class WhistModel extends GenericStandardDeckGame implements CardGameModel<Card> {
  protected List<Player> remainingPlayers;
  protected List<Card> currentHand;
  protected int curPlayerIdx;

  /**
   * Constructs a new WhistModel with the default parameters
   */
  public WhistModel() {
    super();
    this.remainingPlayers = new ArrayList<>();
    this.currentHand = new ArrayList<>();
    this.curPlayerIdx = 0;
  }

  /**
   * Distributes the cards in the specified order among the players in round-robin fashion.
   *
   * <p>Void side effect: sets this GenericStandardDeckGame's players field
   * to the playerList created in this method. Also sets the deck field
   * to be the given deck and the remainingPlayers field to the appropriate
   * list of players.</p>
   *
   * @param numPlayers the number of players in the game
   * @param deck the deck of cards
   */
  @Override
  public void startPlay(int numPlayers, List<Card> deck) {
    super.startPlay(numPlayers, deck);
    this.remainingPlayers = playersWithCardsLeft();
    for (Player p : this.players) {
      Collections.sort(p.getHand());
    }
  }

  /**
   * Determines if any of the cards in the given hand have the same suit as the given suit value.
   *
   * @param hand the given list of Cards
   * @param suitValue the given suit value
   * @return true if there is at least one card in the hand with a suit matching the given
   * suit value, false otherwise.
   */
  protected boolean isSuitInHand(List<Card> hand, Suit suitValue) {
    for (Card c : hand) {
      if (c.getSuit() == suitValue) {
        return true;
      }
    }
    return false;
  }

  /**
   * Determines if the player with the given index has cards left. If they do, it returns the
   * given int. If not, it searches for the next player with cards left in their hand and returns
   * their index number. If no players have cards left, -1 is returned.
   * @param playerIdx the index of the Player whose cards will initially be checked
   * @return the given index if that player still has cards, else the next player's index that has
   * cards. If no players have cards, -1 is returned.
   */
  protected int findNextStarter(int playerIdx) {
    if (players.get(playerIdx).getHand().size() > 0) {
      return playerIdx;
    } else {
      int returnIdx = -1;
      for (int i = 1; i < players.size(); i++) {
        int curIdx = (playerIdx + i) % players.size();
        if (players.get(curIdx).getHand().size() > 0) {
          return curIdx;
        }
      }
      return returnIdx;
    }
  }

  /**
   * Returns a list of the Players with cards remaining. The players are added starting from
   * the player at the curPlayerIdx.
   * @return a list of the Players with cards remaining.
   */
  protected List<Player> playersWithCardsLeft() {
    List<Player> remainingPlayers = new ArrayList<>();
    for (int i = 0; i < players.size(); i++) {
      int curIdx = (curPlayerIdx + i) % players.size();
      Player curPlayer = players.get(curIdx);
      if (curPlayer.getHand().size() > 0) {
        remainingPlayers.add(curPlayer);
      }
    }
    return remainingPlayers;
  }

  /**
   * Determines who won the hand and increments that player's wins. Also sets curPlayerIdx to the
   * winning player's index. If the winning player has no more cards left, curPlayerIdx is set
   * to the next player who does have cards left.
   */
  protected void lastPlayInHand() {
    Suit handSuit = currentHand.get(0).getSuit();
    Card highestCard = currentHand.get(0);
    for (Card c : currentHand) {
      int highestCardRankIndex = highestCard.getRank().ordinal();
      int currentRankIndex = c.getRank().ordinal();
      if (c.getSuit() == handSuit && currentRankIndex < highestCardRankIndex) {
        highestCard = c;
      }
    }
    int winningCardIdx = currentHand.indexOf(highestCard);
    Player winner = remainingPlayers.get(winningCardIdx);
    winner.addWin();
    int winnerIdx = players.indexOf(winner);
    curPlayerIdx = findNextStarter(winnerIdx);
    currentHand.clear(); // resets the current hand
    if (curPlayerIdx >= 0) { // if curPlayerIdx < 0, game is over
      remainingPlayers = playersWithCardsLeft();
    }
  }

  /**
   * @throws IllegalArgumentException if the inputs are invalid (it is not the player's turn, the
   * position cardIdx is invalid), or if the current card cannot be played (i.e. it is of the wrong
   * suit even though the player has a card of the correct suit)
   */
  @Override
  public void play(int playerNo, int cardIdx) {
    if (playerNo != curPlayerIdx) {
      throw new IllegalArgumentException(String.format("It is not Player %d's turn", playerNo));
    }
    List<Card> curPlayerHand = players.get(playerNo).getHand();
    if (cardIdx >= curPlayerHand.size() || cardIdx < 0) {
      throw new IllegalArgumentException(String.format("The position %d is invalid", cardIdx));
    } else if (currentHand.size() < 1) {
      currentHand.add(curPlayerHand.get(cardIdx)); // adds the card to the round hand
      curPlayerHand.remove(cardIdx); // removes the card from the player's hand
      curPlayerIdx = findNextStarter((playerNo + 1) % players.size());
      return; // breaks out of the method
    }
    Suit handSuit = currentHand.get(0).getSuit();
    Card playerCard = curPlayerHand.get(cardIdx);
    if (isSuitInHand(curPlayerHand, handSuit) && playerCard.getSuit() != handSuit) {
      throw new IllegalArgumentException("Played a card of the wrong suit, you have a card " +
        "with the correct suit.");
    } else {
      currentHand.add(playerCard); // adds the card to the round hand
      curPlayerHand.remove(cardIdx); // removes the card from the player's hand
      curPlayerIdx = findNextStarter((playerNo + 1) % players.size());
    }
    if (currentHand.size() == remainingPlayers.size()) {
      lastPlayInHand();
    }
  }

  @Override
  public int getCurrentPlayer() {
    return curPlayerIdx;
  }

  @Override
  public boolean isGameOver() {
    return curPlayerIdx < 0 || remainingPlayers.size() <= 1;
  }

  /**
   * Returns a list containing the Player numbers with the most amount of wins. The
   * Players all have the same score, which is the highest amount of wins in the game.
   *
   * <p>NOTE: This does <b>NOT</b> return their indices. It returns their Player number
   * (index + 1).</p>
   * @return a list of Player numbers of the players sharing the top amount of wins.
   */
  private List<Integer> mostWins() {
    List<Integer> numWins = players.stream().map(Player::getWins).collect(Collectors.toList());
    List<Integer> winners = new ArrayList<>();
    if (numWins.size() > 0) {
      int maxWins = Collections.max(numWins);
      for (int i = 0; i < numWins.size(); i++) {
        if (numWins.get(i) == maxWins) {
          winners.add(i + 1);
        }
      }
    }
    return winners;
  }

  /**
   * Gets the special message for the turn. If the game is over, it returns a String with the
   * winners. Otherwise, a String is returned with the number of the Players whose turn it is.
   * @return the special message for the turn.
   */
  private String getTurnMessage() {
    String message;
    if (isGameOver()) {
      List<Integer> winnerList = mostWins();
      String winners =
        winnerList.stream().map(Object::toString).collect(Collectors.joining(", Player "));
      message = "Game over. Player " + winners + " won.";
    } else {
      message = (String.format("Turn: Player %d", curPlayerIdx + 1));
    }
    return message;
  }

  /**
   * Uses the getGameState from GenericStandardDeckGame and appends additional information
   * at the bottom (each player with the number of hands they've won, as well as a special
   * message declaring the current player or the winners).
   * @return a String that contains the entire state of the game.
   */
  @Override
  public String getGameState() {
    int numPlayers = players.size();
    StringBuilder sb = new StringBuilder(super.getGameState());
    for (int i = 0; i< numPlayers; i++) {
      sb.append(System.lineSeparator());
      int wins = players.get(i).getWins();
      String playerWins = String.format("Player %d: %d hands won", i + 1, wins);
      sb.append(playerWins);
    }
    sb.append(System.lineSeparator());
    sb.append(getTurnMessage());
    return sb.toString();
  }

  @Override
  public boolean equals(Object obj) {
    if (! (obj instanceof WhistModel)) return false;
    WhistModel that = (WhistModel) obj;
    return this.deck == that.deck && this.players == that.players && this.remainingPlayers ==
      that.remainingPlayers && this.currentHand == that.currentHand && this.curPlayerIdx ==
      that.curPlayerIdx;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.deck, this.players, this.remainingPlayers, this.currentHand,
      this.curPlayerIdx);
  }
}
