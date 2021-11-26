package app.closer.testapp.data.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BasicMathExpressionValidator
    implements ConstraintValidator<BasicMathExpression, String> {

  @Override
  public boolean isValid(String expression, ConstraintValidatorContext constraintValidatorContext) {
    log.debug("validating {}", expression);
    return !expression.contains("(");
  }
}
