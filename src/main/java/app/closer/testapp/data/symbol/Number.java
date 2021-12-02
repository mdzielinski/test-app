package app.closer.testapp.data.symbol;

import lombok.Getter;

@Getter
public final class Number extends Symbol {
  public static final String ALLOWED_NUMBER_SYMBOLS = "0123456789";
  private final Double body;

  public static Number from(String symbol) {
    return new Number(symbol);
  }

  private Number(String symbol) {
    this.body = Double.parseDouble(symbol);
  }

  @Override
  public String toString() {
    return body.toString();
  }
}
