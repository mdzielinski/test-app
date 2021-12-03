package app.closer.testapp.data.symbol;

import app.closer.testapp.util.RegexHelper;

public final class Bracket implements Symbol {

  private final boolean isOpening;

  public static Bracket from(String symbol) {
    return new Bracket(symbol);
  }

  private Bracket(String symbol) {
    isOpening = RegexHelper.doPatternMatch(opening_brackets, symbol);
  }

  @Override
  public Double obtain() {
    return null;
  }

  public boolean isOpening() {
    return isOpening;
  }
}
