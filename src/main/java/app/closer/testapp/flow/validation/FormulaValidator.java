package app.closer.testapp.flow.validation;

import app.closer.testapp.data.Formula;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FormulaValidator implements ConstraintValidator<BasicFormula, Formula> {

  @Override
  public boolean isValid(Formula formula, ConstraintValidatorContext constraintValidatorContext) {
    log.debug("validating formula: \"{}\"", formula);
    return BasicFormulaValidator.isValid(formula.getBody());
  }
}
