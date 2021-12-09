package app.closer.testapp.data;

public final class Result {

  private final Double body;

  public Double get() {
    return this.body;
  }

  public static Result of(Double val) {
    return new Result(val);
  }

  public static Result of(double val) {
    return new Result(val);
  }

  private Result(Double body) {
    this.body = body;
  }

  @Override
  public String toString() {
    return body.toString();
  }
}
