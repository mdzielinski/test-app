package app.closer.testapp.data;

import static app.closer.testapp.data.symbol.Bracket.ALLOWED_BRACKET_SYMBOLS;
import static app.closer.testapp.data.symbol.Operator.ALLOWED_OPERATOR_SYMBOLS;
import static app.closer.testapp.data.symbol.SymbolFactory.symbolFrom;

import app.closer.testapp.data.symbol.Bracket;
import app.closer.testapp.data.symbol.Operator;
import app.closer.testapp.data.symbol.Symbol;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentLinkedQueue;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class Expression {

  private final ConcurrentLinkedQueue<Symbol> body;

  public static Expression from(Formula formula) {
    return new Expression(formula.getBody());
  }

  private Expression(String body) {
    this.body = tokenize(body);
  }

  private ConcurrentLinkedQueue<Symbol> tokenize(String expression) {
    ConcurrentLinkedQueue<Symbol> symbols = new ConcurrentLinkedQueue<>();
    var tokenizer = new StringTokenizer(expression, deliminatorsOfBracketsAndOperators(), true);

    int currentPriority = 0;
    while (tokenizer.hasMoreTokens()) {
      var symbol = symbolFrom(tokenizer.nextToken());
      var aClass = symbol.getClass();

      if (Bracket.class.equals(aClass)) {
        currentPriority += ((Bracket) symbol).isOpening() ? 10 : (-10);
      }

      if (Operator.class.equals(aClass)) {
        ((Operator) symbol).changePriorityBy(currentPriority);
      }

      symbols.add(symbol);
    }
    System.out.println(symbols);
    return symbols;
  }

  private static String deliminatorsOfBracketsAndOperators() {
    return ALLOWED_BRACKET_SYMBOLS + ALLOWED_OPERATOR_SYMBOLS;
  }

  @Override
  public String toString() {
    return body.toString();
  }
}
