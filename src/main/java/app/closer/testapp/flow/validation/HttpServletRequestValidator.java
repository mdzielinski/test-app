package app.closer.testapp.flow.validation;

import app.closer.testapp.util.FormulaExtractor;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpServletRequestValidator
    implements ConstraintValidator<BasicFormula, HttpServletRequest> {

  @Override
  public boolean isValid(
      HttpServletRequest request, ConstraintValidatorContext constraintValidatorContext) {
    String formula = FormulaExtractor.extractFrom(request);
    log.debug("validating formula: \"{}\"", formula);
    return BasicFormulaValidator.isValid(formula);
  }
}
