package app.closer.testapp.data.validation;

import app.closer.testapp.util.FormulaExtractor;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpServletRequestValidator
    implements ConstraintValidator<BasicExpression, HttpServletRequest> {

  @Override
  public boolean isValid(
      HttpServletRequest request, ConstraintValidatorContext constraintValidatorContext) {
    String expression = FormulaExtractor.extractFrom(request);
    log.debug("validating expression: \"{}\"", expression);
    return BasicExpressionValidator.isValid(expression);
  }
}
