package app.closer.testapp.flow.validation;

import app.closer.testapp.data.Expression;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExpressionValidator implements ConstraintValidator<BasicExpression, Expression> {

  @Override
  public boolean isValid(
      Expression expression, ConstraintValidatorContext constraintValidatorContext) {
    log.debug("validating formula: \"{}\"", expression);
    return Validator.isValid(expression.getBody());
  }
}
