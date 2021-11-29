package app.closer.testapp.flow.validation;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RUNTIME)
@Target(PARAMETER)
@Constraint(validatedBy = {FormulaValidator.class, HttpServletRequestValidator.class})
public @interface BasicFormula {
  Class<?>[] groups() default {};

  String message() default
      "Invalid mathematical formula. Allowed set of symbols: "
          + "\"1233456780+-/*(),.\" with respect to mathematical formula rules. Both comma \",\" "
          + "and dot \".\" are considered proper decimal separators.";

  Class<? extends Payload>[] payload() default {};
}
