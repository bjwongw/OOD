package cs3500.hw04;

import cs3500.hw03.WhistModel;

/**
 * Class to create WhistModels
 */
public class WhistModelCreator {

  /**
   * Enum class to represent whether the associated WhistModel implements Trump cards or not.
   */
  public enum ModelType { TRUMP, NOTRUMP }

  /**
   * Returns either a WhistModel or an object of the trump model, depending on the value of the
   * parameter.
   * @param type the ModelType to instantiate
   * @return a WhistModel of the given model type
   * @throws IllegalArgumentException if the ModelType is invalid
   */
  public static WhistModel create(ModelType type) {
    if (type == ModelType.TRUMP) return new WhistTrumpModel();
    else if (type == ModelType.NOTRUMP) return new WhistModel();
    else throw new IllegalArgumentException("Given something other than a legal ModelType");
  }
}
