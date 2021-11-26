package app.closer.testapp.data.validation;

import static org.apache.logging.log4j.util.Strings.isBlank;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BasicExpressionValidator implements ConstraintValidator<BasicExpression, String> {

  @Override
  public boolean isValid(String expression, ConstraintValidatorContext constraintValidatorContext) {
    log.debug("validating expression: \"{}\"", expression);
    return !isBlank(expression) && charsAreAllowed(expression);
  }

  private boolean charsAreAllowed(String expression) {
    return expression.matches("^[0-9,.\\-+()*\\\\\\s]+$");
  }
}
