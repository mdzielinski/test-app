package app.closer.testapp.flow.validation;

import static app.closer.testapp.data.symbol.Bracket.ALLOWED_BRACKET_SYMBOLS;
import static app.closer.testapp.data.symbol.Number.ALLOWED_NUMBER_SYMBOLS;
import static app.closer.testapp.data.symbol.Operator.ALLOWED_OPERATOR_SYMBOLS;
import static org.apache.logging.log4j.util.Strings.isBlank;

import java.util.regex.Pattern;

interface BasicFormulaValidator {

  String ALLOWED_DELIMITER_SYMBOLS = ",.";
  String ALLOWED_TECHNICAL_SYMBOLS = "%20";

  static boolean isValid(String formula) {
    var regex = Pattern.compile(getWholeExpressionRegex(provideSymbols()));
    return !isBlank(formula) && regex.matcher(formula).matches();
  }

  private static String getWholeExpressionRegex(String content) {
    return "^[" + content + "]+$";
  }

  static String provideSymbols() {
    return ALLOWED_BRACKET_SYMBOLS
        + ALLOWED_NUMBER_SYMBOLS
        + ALLOWED_OPERATOR_SYMBOLS
        + ALLOWED_DELIMITER_SYMBOLS
        + ALLOWED_TECHNICAL_SYMBOLS;
  }
}
