package app.closer.testapp.data.symbol;

import lombok.Getter;

@Getter
public final class Number extends Symbol {
  public static final String ALLOWED_NUMBER_SYMBOLS = "0123456789";
  private final Double number;

  public Number(String symbol) {
    this.number = Double.parseDouble(symbol);
  }
}
