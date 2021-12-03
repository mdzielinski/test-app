package app.closer.testapp.data.symbol;

import lombok.Getter;

@Getter
public final class Number implements Symbol {

  private final Double body;

  public static Number from(Double number) {
    return new Number(number);
  }

  public static Number from(String symbol) {
    return new Number(symbol);
  }

  private Number(String symbol) {
    this.body = Double.parseDouble(symbol);
  }

  public Number(Double number) {
    this.body = number;
  }

  @Override
  public Double obtain() {
    return null;
  }

  @Override
  public String toString() {
    return body.toString();
  }
}
