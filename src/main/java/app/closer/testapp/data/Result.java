package app.closer.testapp.data;

import java.math.BigDecimal;

public final class Result {

  private final BigDecimal body;

  public BigDecimal get() {
    return this.body;
  }

  public static Result of(BigDecimal val) {
    return new Result(val);
  }

  public static Result of(double val) {
    return new Result(BigDecimal.valueOf(val));
  }

  private Result(BigDecimal body) {
    this.body = body;
  }

  @Override
  public String toString() {
    return body.toString();
  }
}
