package app.closer.testapp.flow.validation;

import static org.apache.logging.log4j.util.Strings.isBlank;

interface BasicFormulaValidator {

  static boolean isValid(String formula) {
    return !isBlank(formula) && formula.matches("^[0-9,.\\-+*/()\\s%]+$");
  }
}
