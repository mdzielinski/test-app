package app.closer.testapp.data;

import app.closer.testapp.data.symbol.Symbol;
import java.util.LinkedList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Expression {

  private final LinkedList<Symbol> expression;

  public Expression(String expression) {
    this.expression = tokenize(expression);
  }

  private LinkedList<Symbol> tokenize(String expression) {
    return null;//todo
  }

}
