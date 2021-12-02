package app.closer.testapp.data;

import org.junit.jupiter.api.Test;

class ExpressionTest {

  @Test
  void name() {
    Formula formula = Formula.from("(1+1)");
    System.out.println(Expression.from(formula)); // todo
  }
}
