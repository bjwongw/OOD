package cs3500.hw04;

import cs3500.hw02.Card;
import cs3500.hw02.Player;
import cs3500.hw02.Suit;
import cs3500.hw03.WhistModel;

import java.util.List;

/**
 * Specifies any operations for a Whist card game with the trump variation.
 *
 * <p>Whist is a trick-based card game that can be played by two or more players. The game uses a
 * standard 52-card deck. The objective of the game is for each player to win as many hands as
 * possible. The game ends when all the players have run out of cards, and the player with the
 * maximum number of hands wins the game.</p>
 *
 * <p>In this variation, one of the four suits is declared to be a trump suit at the start of a
 * game. One of the players (the dealer) distributes the cards in a round-robin fashion to all the
 * players. Play then starts with the player sitting next to the dealer (in the dealing order).
 * The player who starts a hand is free to play any card. This card determines the suit of the
 * current hand. All other players must play a card of the same suit if they have one. However,
 * if a player does not have a card of the same suit, he/she has two choices. The first choice
 * is to play a card of some other suit and essentially “forfeit” this hand. However the player
 * can also play a card of the trump suit. Once a card of the trump suit is played in a hand, the
 * winner of the hand is the player who played a card of the trump suit of the highest value. That
 * is, once a card of the trump suit is played, the values of the hand’s suit do not matter anymore
 * for that hand. If the suit of the current hand is the trump suit (i.e. the first player in the
 * hand played a card of the trump suit) then the hand proceeds normally as before: the card of
 * this suit with the highest value wins the hand.</p>
 *
 * <p>When the last player (the one sitting just before the one who started the hand) has played
 * the winner of the hand is determined. The winner of a hand is the player who played the card of
 * the correct suit and the highest value. The winner collects the cards in the hand and keeps
 * them aside, and then starts the next hand.</p>
 */
public class WhistTrumpModel extends WhistModel {
  private Suit trump;

  /**
   * Default constructor for the WhistTrumpModel
   */
  public WhistTrumpModel() {
    super();
    this.trump = null;
  }

  @Override
  public void startPlay(int numPlayers, List<Card> deck) {
    super.startPlay(numPlayers, deck);
    this.trump = deck.get(0).getSuit();
  }

  @Override
  protected void lastPlayInHand() {
    Suit handSuit = currentHand.get(0).getSuit();
    Card highestCard = currentHand.get(0);
    boolean isHighestTrump = false;
    for (Card c : currentHand) {
      int highestCardRankIndex = highestCard.getRank().ordinal();
      int currentRankIndex = c.getRank().ordinal();
      if (!isHighestTrump) {
        if (c.getSuit() == handSuit && currentRankIndex < highestCardRankIndex) {
          highestCard = c;
        } else if (c.getSuit() == this.trump) {
          highestCard = c;
          isHighestTrump = true;
        }
      } else {
        if (c.getSuit() == this.trump && currentRankIndex < highestCardRankIndex) {
          highestCard = c;
        }
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

  @Override
  public String getGameState() {
    String originalMessage = super.getGameState();
    String additionalInfo = "\nTrump: ";
    if (this.trump != null) additionalInfo += this.trump.toString();
    return originalMessage.concat(additionalInfo);
  }
}
