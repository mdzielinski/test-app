package app.closer.testapp.data.symbol;

import lombok.Getter;

@Getter
public final class Operator extends Symbol {
  public static final String ALLOWED_OPERATOR_SYMBOLS = "\\+-\\\\*/";

  private final Character body;

  public static Operator form(String symbol) {
    return new Operator(symbol.charAt(0));
  }

  private Operator(Character body) {
    this.body = body;
  }

  @Override
  public String toString() {
    return body.toString();
  }
}
