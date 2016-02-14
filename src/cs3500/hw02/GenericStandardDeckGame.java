package cs3500.hw02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Specifies operations for any generic standard deck game.
 */
public class GenericStandardDeckGame implements GenericCardGameModel<Card> {
  protected List<Card> deck;
  protected List<Player> players;

  /**
   * Constructs a new generic standard deck game with the default parameters
   */
  public GenericStandardDeckGame() {
    this.deck = new ArrayList<>();
    this.players = new ArrayList<>();
  }

  /**
   * Returns this game's list of Players.
   *
   * @return the list of Players.
   */
  public List<Player> getPlayers() {
    return players;
  }

  /**
   * Creates a list of cards in alphabetical order of suits. Within each suit, cards are
   * ordered by rank in descending order, with aces as the highest value (i.e. A, K, Q, ..., 2).
   *
   * @return a list of 52 Cards
   */
  @Override
  public List<Card> getDeck() {
    List<Card> deck = new ArrayList<>();
    for (Suit s : Suit.values()) {
      for (Rank r : Rank.values()) {
        Card newCard = new Card(r, s);
        deck.add(newCard);
      }
    }
    return deck;
  }

  /**
   * Determines if the given deck is a standard deck of cards. It's a standard deck
   * if it contains 52 cards, without duplicates, and has every suit and rank combination
   * included.
   *
   * @param givenDeck the deck given
   * @return true if the given deck is a standard deck, false if not
   */
  private Boolean isStandardDeck(List<Card> givenDeck) {
    if (givenDeck == null || givenDeck.size() != 52) {
      return false;
    }
    List<Card> standardDeck = getDeck();
    return standardDeck.containsAll(givenDeck) && givenDeck.containsAll(standardDeck);
  }

  /**
   * Distributes the cards in the specified order among the players in round-
   * robin fashion.
   *
   * <p>Void side effect: sets this GenericStandardDeckGame's players field
   * to the playerList created in this method. Also sets the deck field
   * to be the given deck.</p>
   *
   * @param numPlayers the number of players in the game
   * @param deck the deck of cards
   */
  @Override
  public void startPlay(int numPlayers, List<Card> deck) {
    if (numPlayers <= 1) {
      throw new IllegalArgumentException("Error: Cannot play a game with 1 or less players.");
    }
    if (!isStandardDeck(deck)) {
      throw new IllegalArgumentException("Error: deck given was invalid.");
    }
    List<Player> playerList = new ArrayList<>();
    for (int i = 0; i < numPlayers; i++) {
      playerList.add(new Player());
    }
    for (int i = 0; i < deck.size(); i++) {
      int pos = i % numPlayers;
      playerList.get(pos).addToHand(deck.get(i));
    }
    this.deck = deck;
    this.players = playerList;
  }

  @Override
  public String getGameState() {
    int numPlayers = players.size();
    String firstLine = String.format("Number of Players: %d", numPlayers);
    StringBuilder sb = new StringBuilder(firstLine);
    for (int i = 0; i < numPlayers; i++) {
      sb.append(System.lineSeparator());
      Player curPlayer = this.players.get(i);
      List<Card> curPlayerHand = new ArrayList<>(curPlayer.getHand());
      Collections.sort(curPlayerHand);
      String cards = curPlayerHand.stream().map(Card::toString).collect(Collectors.joining(", "));
      String playerId = String.format("Player %d: ", i + 1);
      sb.append(playerId.concat(cards));
    }
    return sb.toString();
  }
}
