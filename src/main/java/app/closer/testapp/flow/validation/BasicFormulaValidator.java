package app.closer.testapp.flow.validation;

import static app.closer.testapp.data.symbol.Symbol.BRACKET_SYMBOLS;
import static app.closer.testapp.data.symbol.Symbol.NUMBER_SYMBOLS;
import static app.closer.testapp.data.symbol.Symbol.OPERATOR_SYMBOLS;
import static org.apache.logging.log4j.util.Strings.isBlank;

import java.util.regex.Pattern;

interface BasicFormulaValidator {

  String ALLOWED_DELIMITER_SYMBOLS = ".";
  String ALLOWED_TECHNICAL_SYMBOLS = "%20";

  static boolean isValid(String formula) {
    var regex = Pattern.compile(wholeExpressionRegexFrom(provideSymbols()));
    return !isBlank(formula) && regex.matcher(formula).matches();
    //todo check for more than 3 symbols in a row
    //todo check for 2 symbols in a row, between numbers (it's ok if second is minus)
    //todo check for symbol at the beginning (it's ok if symbol is minus)
    //todo check for two dots in number
    //todo check for no leading numbers before dot
  }

  private static String wholeExpressionRegexFrom(String content) {
    return "^[" + content + "]+$";
  }

  static String provideSymbols() {
    return BRACKET_SYMBOLS
        + NUMBER_SYMBOLS
        + OPERATOR_SYMBOLS
        + ALLOWED_DELIMITER_SYMBOLS
        + ALLOWED_TECHNICAL_SYMBOLS;
  }
}
