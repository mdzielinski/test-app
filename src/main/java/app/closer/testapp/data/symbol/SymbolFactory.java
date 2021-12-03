package app.closer.testapp.data.symbol;

import static app.closer.testapp.data.symbol.Symbol.ALLOWED_BRACKET_SYMBOLS;
import static app.closer.testapp.data.symbol.Symbol.ALLOWED_OPERATOR_SYMBOLS;
import static app.closer.testapp.util.RegexHelper.doPatternMatch;

public class SymbolFactory {

  public static Symbol symbolFrom(String symbol, int order) {

    if (doPatternMatch(ALLOWED_BRACKET_SYMBOLS, symbol)) return Bracket.from(symbol);
    if (doPatternMatch(ALLOWED_OPERATOR_SYMBOLS, symbol)) return Operator.form(symbol, order);
    return Number.from(symbol);
  }
}
