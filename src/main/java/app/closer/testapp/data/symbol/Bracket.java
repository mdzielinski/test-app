package app.closer.testapp.data.symbol;

public final class Bracket extends Symbol {
  public static final String ALLOWED_BRACKET_SYMBOLS = "()";
  private static final String opening_brackets = "(";
  private final boolean isOpening;

  public Bracket(String bracket) {
    isOpening = opening_brackets.contains(bracket);
  }

  public boolean isOpening() {
    return isOpening;
  }
}
