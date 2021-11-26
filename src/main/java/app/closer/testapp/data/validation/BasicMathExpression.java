package app.closer.testapp.data.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = BasicMathExpressionValidator.class)
public @interface BasicMathExpression {
  String message() default
      "Invalid mathematical expression. Please use this set of symbols: "
          + "\"1233456780+-/*(),.\" with respect to mathematical expression rules. Both comma \",\" "
          + "and dot \".\" are considered proper decimal separators.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
