package app.closer.testapp.data;

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
    var tokenizer =
        new StringTokenizer(
            expression, Bracket.ALLOWED_BRACKET_SYMBOLS + Operator.ALLOWED_OPERATOR_SYMBOLS, true);
    int priorityCounter = 0;
    while (tokenizer.hasMoreTokens()) {
      var symbol = symbolFrom(tokenizer.nextToken());
      System.out.println(symbol.getClass() + symbol.toString());
      symbols.add(symbol);
    }
    return symbols;
  }

  @Override
  public String toString() {
    return body.toString();
  }
}
