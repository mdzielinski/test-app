package app.closer.testapp.data.symbol;

public final class Bracket extends Symbol {
  public static final String ALLOWED_BRACKET_SYMBOLS = "()";
  private static final String opening_brackets = "(";
  private final Character body;

  public static Bracket from(String symbol) {
    return new Bracket(symbol);
  }

  private Bracket(String symbol) {
    body = symbol.charAt(0);
  }

  @Override
  public String toString() {
    return body.toString();
  }

  public boolean isOpening() {
    return opening_brackets.contains(body.toString());
  }
}
