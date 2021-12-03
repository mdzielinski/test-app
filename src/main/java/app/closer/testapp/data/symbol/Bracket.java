package app.closer.testapp.data.symbol;

import app.closer.testapp.util.RegexHelper;

public final class Bracket extends Symbol {
  public static final String ALLOWED_BRACKET_SYMBOLS = "()";
  private static final String opening_brackets = "(";
  private final boolean isOpening;

  public static Bracket from(String symbol) {
    return new Bracket(symbol);
  }

  private Bracket(String symbol) {
    isOpening = RegexHelper.doPatternMatch(opening_brackets, symbol);
  }

  public boolean isOpening() {
    return isOpening;
  }
}
