package app.closer.testapp.flow.validation;

import static app.closer.testapp.data.symbol.Symbol.BRACKET_SYMBOLS;
import static app.closer.testapp.data.symbol.Symbol.NUMBER_SYMBOLS;
import static app.closer.testapp.data.symbol.Symbol.OPERATOR_SYMBOLS;
import static java.util.regex.Pattern.compile;
import static org.apache.logging.log4j.util.Strings.isBlank;

import lombok.experimental.UtilityClass;

@UtilityClass
class Validator {

  String ALLOWED_DELIMITER_SYMBOLS = ".";
  String ALLOWED_TECHNICAL_SYMBOLS = "%20";

  static boolean isValid(String formula) {
    return !isBlank(formula) && containsOnlyAllowedSymbols(formula);
  }

  private static boolean containsOnlyAllowedSymbols(String formula) {
    var pattern = compile(wholeExpressionRegexFrom(provideSymbols()));
    return pattern.matcher(formula).matches();
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
