package app.closer.testapp.flow.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import app.closer.testapp.data.Expression;
import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SimpleCalculatorTest {

  private SimpleCalculator calculator;

  @Nested
  class basic_adding {
    @ParameterizedTest
    @MethodSource("positiveAndNegativeValues")
    void should_add(String expression, String result) {

      assertEquals(calculator.calculate(Expression.from(expression)).toString(), result);
    }

    static Stream<Arguments> positiveAndNegativeValues() {
      return Stream.of(
          arguments("0+0", "0.0"),
          arguments("-1+1", "0.0"),
          arguments("1+-1", "0.0"),
          arguments("-1+-1", "-2.0"));
    }
  }

  @Nested
  class basic_multiplication {
    @ParameterizedTest
    @MethodSource("basicMultiplication")
    void should_multiply(String expression, String result) {

      assertEquals(calculator.calculate(Expression.from(expression)).toString(), result);
    }

    static Stream<Arguments> basicMultiplication() {
      return Stream.of(
          arguments("0*0", "0.0"),
          arguments("1*0", "0.0"),
          arguments("-1*1", "-1.0"),
          arguments("-1*-1", "1.0"),
          arguments("1*-1", "-1.0"));
    }
  }

  @Nested
  class basic_brackets {
    @ParameterizedTest
    @MethodSource("simpleBracketsUsage")
    void should_consider_brackets(String expression, String result) {

      assertEquals(calculator.calculate(Expression.from(expression)).toString(), result);
    }

    static Stream<Arguments> simpleBracketsUsage() {
      return Stream.of(
          arguments("(2+2)*2", "8.0"),
          arguments("2+2*2", "6.0"),
          arguments("5*(5*0)+1", "1.0"),
          arguments("5+2*(3-2)", "7.0"));
    }
  }
}
