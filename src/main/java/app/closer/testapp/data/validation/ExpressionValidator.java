package app.closer.testapp.data.validation;

import app.closer.testapp.data.Expression;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExpressionValidator implements ConstraintValidator<BasicExpression, Expression> {

  @Override
  public boolean isValid(
      Expression expression, ConstraintValidatorContext constraintValidatorContext) {
    log.debug("validating expression: \"{}\"", expression);
    return BasicExpressionValidator.isValid(expression.getBody());
  }
}
