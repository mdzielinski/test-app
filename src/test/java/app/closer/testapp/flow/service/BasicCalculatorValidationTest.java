package app.closer.testapp.flow.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import app.closer.testapp.data.Expression;
import app.closer.testapp.dataflow.ExpressionParsingException;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BasicCalculatorValidationTest {

  private BasicCalculator calculator;

  @BeforeEach
  void setUp() {
    calculator = new BasicCalculator();
  }

  @Nested
  class malformed_brackets {
    static Stream<Arguments> malformedBrackets() {
      return Stream.of(
          arguments(")5*0*5+1("),
          arguments(")(1+0*0*5"),
          arguments("0)*0*(5"),
          arguments("((1+0)*0*5"),
          arguments("(1+0*0*5))"),
          arguments("1+(0*0*5"),
          arguments("1+0)*0*5"),
          arguments("(1+0*0*5"),
          arguments("(1+0*0*5"),
          arguments("1+0*0*5)"),
          arguments("(1+0*0*5("),
          arguments("(1+0*0*5"),
          arguments("5+2*(3-2("));
    }

    @ParameterizedTest
    @MethodSource("malformedBrackets")
    void should_throw_because_brackets_malformed(String formula) {
      assertThrows(
          ExpressionParsingException.class, () -> calculator.evaluate(Expression.from(formula)));
    }
  }
}
