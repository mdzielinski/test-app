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
          arguments("()(2+2)*2"),
          arguments("2*(2(+)2)"),
          arguments("0()+(2+2)*2+0"),
          arguments("2(+2)*2"),
          arguments("1+2(*2)+1"),
          arguments("5*(5*(0)+)1"),
          arguments("1+(5*0)*5()"),
          arguments(")5*0*5+1("),
          arguments(")(1+0*0*5"),
          arguments("0)*0*(5"),
          arguments("((1+0)*0*5"),
          arguments("(1+0*0*5))"),
          arguments("1+(0*0*5"),
          arguments("1+0)*0*5"),
          arguments("(1+0*0*5"),
          arguments("1(+)0*0*5"),
          arguments("(1+0*0*5"),
          arguments("1+0*0*5)"),
          arguments("(1+0)*0*5"),
          arguments("1(+0)*0*5"),
          arguments("(1+0*0*5("),
          arguments("(1+0*0*5"),
          arguments("(1+0)*0*5"),
          arguments("5+2*(3-2("));
    }

    @ParameterizedTest
    @MethodSource("malformedBrackets")
    void should_throw_because_brackets_malformed(String formula) {
      assertThrows(
          ExpressionParsingException.class, () -> calculator.evaluate(Expression.from(formula)));
    }
  }

  @Nested
  class malformed_operators {
    static Stream<Arguments> malformedOperators() {
      return Stream.of(
          arguments("1+1+6+"),
          arguments("1+1+6-"),
          arguments("-+1+1+6-"),
          arguments("-1-+1+6-"),
          arguments("-1--1+6-"),
          arguments("-1--1+6-"),
          arguments("1+1+1+1+1"),
          arguments("1+1+1+1+1+"),
          arguments("1+1+1+1+1*"),
          arguments("1+1+1+1+1/"),
          arguments("+1+1+1+1+1"),
          arguments("*1+1+1+1+1"),
          arguments("/1+1+1+1+1"),
          arguments("1+1+1+1+1"),
          arguments("1+1+/1+1+1"),
          arguments("1+1-/1+1+1"),
          arguments("1+1*/1+1+1"),
          arguments("1+1/*1+1+1"),
          arguments("1+1++1+1+1"),
          arguments("1142131.3142*/12314213"));
    }

    @ParameterizedTest
    @MethodSource("malformedOperators")
    void  should_throw_because_operators_malformed(String formula) {
      assertThrows(
          ExpressionParsingException.class, () -> calculator.evaluate(Expression.from(formula)));
    }
  }

  @Nested
  class malformed_numbers {
    static Stream<Arguments> malformedNumbers() {
      return Stream.of(
          arguments("1+1,1+1"),
          arguments("1+1283492e-234+1"),
          arguments("1.1+1.1"),
          arguments("1+1234.23423524.234524+1"),
          arguments("1+1343$+1"));
    }

    @ParameterizedTest
    @MethodSource("malformedNumbers")
    void should_throw_because_numbers_malformed(String formula) {
      assertThrows(
          NumberFormatException.class, () -> calculator.evaluate(Expression.from(formula)));
    }
  }
}
