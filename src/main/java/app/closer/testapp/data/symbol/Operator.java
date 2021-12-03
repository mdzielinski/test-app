package app.closer.testapp.data.symbol;

import static app.closer.testapp.util.RegexHelper.doPatternMatch;

import lombok.Getter;

@Getter
public final class Operator implements Symbol {

  private static final int priority_change_per_level = 2;
  private final char body;
  private final int order;
  private int priority;

  public void changePriorityBy(int levels) {
    priority += levels * priority_change_per_level;
  }

  public static Operator form(String symbol, int order) {
    return new Operator(symbol.charAt(0), order);
  }

  private Operator(Character operator, int order) {
    this.body = operator;
    this.order = order;
    priority = isPriorityOperator(operator) ? 1 : 0;
  }

  private boolean isPriorityOperator(Character body) {
    return doPatternMatch(PRIORITY_OPERATOR_SYMBOLS, body.toString());
  }

  @Override
  public Double obtain() {
    return null;
  }

  @Override
  public String toString() {
    return body + "<P:" + priority + ";O:" + order + ">";
  }
}
