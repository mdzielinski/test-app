package app.closer.testapp.data.symbol;

public final class Bracket extends Symbol {
  private final boolean isOpening;

  public static Bracket from(String symbol) {
    return new Bracket(symbol);
  }

  private Bracket(String symbol) {
    isOpening = OPENING_BRACKETS_SYMBOLS.contains(symbol);
  }

  public boolean isOpening() {
    return isOpening;
  }
}

