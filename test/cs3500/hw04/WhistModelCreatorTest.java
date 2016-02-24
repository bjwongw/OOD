package cs3500.hw04;

import cs3500.hw02.Card;
import cs3500.hw03.CardGameModel;
import cs3500.hw03.WhistModel;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the WhistModelCreator class
 */
public class WhistModelCreatorTest {

  /**
   * Tests for the method create. Ensures that the returned WhistModel is a new, unaltered
   * WhistModel.
   */
  @Test
  public void testCreate_whistModel() {
    CardGameModel<Card> createdWhistModel =
      WhistModelCreator.create(WhistModelCreator.ModelType.NOTRUMP);
    CardGameModel<Card> whistModel = new WhistModel();
    assertTrue(createdWhistModel instanceof WhistModel);
    assertFalse(createdWhistModel instanceof WhistTrumpModel);
    assertEquals(whistModel.getCurrentPlayer(), createdWhistModel.getCurrentPlayer());
    assertEquals(whistModel.isGameOver(), createdWhistModel.isGameOver());
    assertEquals(whistModel.getGameState(), createdWhistModel.getGameState());
  }

  /**
   * Tests for the method create. Ensures that the returned WhistTrumpModel is a new, unaltered
   * WhistTrumpModel.
   */
  @Test
  public void testCreate_whistTrumpModel() {
    CardGameModel<Card> createdTrumpModel =
      WhistModelCreator.create(WhistModelCreator.ModelType.TRUMP);
    CardGameModel<Card> trumpModel = new WhistTrumpModel();
    assertTrue(createdTrumpModel instanceof WhistModel);
    assertTrue(createdTrumpModel instanceof WhistTrumpModel);
    assertEquals(trumpModel.getCurrentPlayer(), createdTrumpModel.getCurrentPlayer());
    assertEquals(trumpModel.getGameState(), createdTrumpModel.getGameState());
    assertEquals(trumpModel.isGameOver(), createdTrumpModel.isGameOver());
  }

  /**
   * Ensures that the create method will return an IllegalArgumentException if it's given
   * a null input.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCreate_nullInput() {
    WhistModelCreator.create(null);
  }
}
