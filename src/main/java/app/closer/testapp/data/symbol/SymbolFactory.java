package app.closer.testapp.data.symbol;

import static app.closer.testapp.data.symbol.Symbol.BRACKET_SYMBOLS;
import static app.closer.testapp.data.symbol.Symbol.OPERATOR_SYMBOLS;
import static app.closer.testapp.util.RegexHelper.patternMatches;

public class SymbolFactory {

  public static Symbol symbolFrom(String symbol, int order, int priority) {

    if (patternMatches(OPERATOR_SYMBOLS, symbol)) return Operator.form(symbol, order, priority);
    if (patternMatches(BRACKET_SYMBOLS, symbol)) return Bracket.from(symbol);
    return Number.from(symbol);
  }
}
