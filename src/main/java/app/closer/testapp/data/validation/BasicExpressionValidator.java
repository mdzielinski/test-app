package app.closer.testapp.data.validation;

import static org.apache.logging.log4j.util.Strings.isBlank;

interface BasicExpressionValidator {

  static boolean isValid(String expression) {
    return !isBlank(expression) && expression.matches("^[0-9,.\\-+*/()\\s]+$");
  }
}
