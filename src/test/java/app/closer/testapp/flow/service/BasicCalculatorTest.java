package app.closer.testapp.flow.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import app.closer.testapp.data.Expression;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BasicCalculatorTest {

  private BasicCalculator calculator;

  @BeforeEach
  void setUp() {
    calculator = new BasicCalculator();
  }

  @Nested
  class basic_adding {
    static Stream<Arguments> positiveValuesInAdding() {
      return Stream.of(
          arguments("0+0", "0.0"),
          arguments("1+0", "1.0"),
          arguments("1+1", "2.0"),
          arguments("1+1+1", "3.0"),
          arguments("1+5+1", "7.0"),
          arguments("0+1+1+2+3+5+8+13", "33.0"),
          arguments("1+1+2+3+5+8+13", "33.0"),
          arguments("13+1+2+3+5+8+1", "33.0"));
    }

    @ParameterizedTest
    @MethodSource("positiveValuesInAdding")
    void should_add(String formula, String result) {
      assertEquals(result, calculator.evaluate(Expression.from(formula)).toString());
    }
  }

  @Nested
  class negative_values {
    static Stream<Arguments> operationsOnNegativeValues() {
      return Stream.of(
          arguments("1+-0", "1.0"),
          arguments("-1+-0", "-1.0"),
          arguments("1+-1", "0.0"),
          arguments("-1+-1", "-2.0"),
          arguments("1*-1", "-1.0"),
          arguments("1+1+-1", "1.0"),
          arguments("-1+-1+-1", "-3.0"),
          arguments("1+5/-1", "-4.0"),
          arguments("-1+-5/-1", "4.0"),
          arguments("1+5*-1", "-4.0"),
          arguments("-1+-5*-1", "4.0"),
          arguments("0-1+-5*-1", "4.0"),
          arguments("-0-1+-5*-1", "4.0"),
          arguments("-1+0+-5*-1", "4.0"),
          arguments("-1+-0-5*-1", "4.0"),
          arguments("-1+-5*-0-1", "-2.0"),
          arguments("-1+-5*0-1", "-2.0"),
          arguments("-1+-5*0-1", "-2.0"),
          arguments("-1+-5*-1+0-0", "4.0"));
    }

    @ParameterizedTest
    @MethodSource("operationsOnNegativeValues")
    void should_perform_operations_on_negative_values(String formula, String result) {
      assertEquals(result, calculator.evaluate(Expression.from(formula)).toString());
    }
  }

  @Nested
  class adding_and_subtraction {
    static Stream<Arguments> negativeValuesInAdding() {
      return Stream.of(
          arguments("-1+1", "0.0"),
          arguments("1-1", "0.0"),
          arguments("-1-1", "-2.0"),
          arguments("0-1+1-2-3-5+8+13", "11.0"));
    }

    @ParameterizedTest
    @MethodSource("negativeValuesInAdding")
    void should_add(String formula, String result) {

      assertEquals(result, calculator.evaluate(Expression.from(formula)).toString());
    }
  }

  @Nested
  class basic_multiplication {
    static Stream<Arguments> basicMultiplication() {
      return Stream.of(
          arguments("0*0", "0.0"),
          arguments("1*0", "0.0"),
          arguments("-1*1", "-1.0"),
          arguments("-1*-1", "1.0"),
          arguments("1*-1", "-1.0"));
    }

    @ParameterizedTest
    @MethodSource("basicMultiplication")
    void should_multiply(String formula, String result) {
      assertEquals(result, calculator.evaluate(Expression.from(formula)).toString());
    }
  }

  @Nested
  class basic_dividing {
    static Stream<Arguments> basicDividing() {
      return Stream.of(
          arguments("1/1", "1.0"),
          arguments("1/2", "0.5"),
          arguments("1/4", "0.25"),
          arguments("1/10", "0.1"),
          arguments("11/10", "1.1"));
    }

    @ParameterizedTest
    @MethodSource("basicDividing")
    void should_multiply(String formula, String result) {
      assertEquals(result, calculator.evaluate(Expression.from(formula)).toString());
    }
  }

  @Nested
  class basic_brackets {
    static Stream<Arguments> bracketsUsage() {
      return Stream.of(
          arguments("(2+2)*2", "8.0"),
          arguments("2*(2+2)", "8.0"),
          arguments("0+(2+2)*2+0", "8.0"),
          arguments("0+2*(2+2)+0", "8.0"),
          arguments("0+2*(2+2)*1+0", "8.0"),
          arguments("0+2*(0+2+2)*1+0", "8.0"),
          arguments("0+2*(0+2+0+2)*1+0", "8.0"),
          arguments("0+2*(0+2+2-0)*1+0", "8.0"),
          arguments("0+2*(0+2-0+2-0)*1+0", "8.0"),
          arguments("0+2*(0+2-0+2-0)*1-0+0", "8.0"),
          arguments("0+2*(0+2-0+2-0)*1-0+0+1", "9.0"),
          arguments("0+2*(0+2-0+2-0)*1-0+0+1", "9.0"),
          arguments("1+0+2*(0+2-0+2-0)*1-0+0", "9.0"),
          arguments("1+0+2*(0+2-0+2-0)*1-0+0+1", "10.0"),
          arguments("2+2*2", "6.0"),
          arguments("2*2+2", "6.0"),
          arguments("1+2*2+1", "6.0"),
          arguments("5*(5*0)+1", "1.0"),
          arguments("1+(5*0)*5", "1.0"),
          arguments("5*0*5+1", "1.0"),
          arguments("1+0*0*5", "1.0"),
          arguments("5+2*(3-2)", "7.0"));
    }

    @ParameterizedTest
    @MethodSource("bracketsUsage")
    void should_consider_brackets(String formula, String result) {
      assertEquals(result, calculator.evaluate(Expression.from(formula)).toString());
    }
  }

  @Nested
  class complex_formula {
    static Stream<Arguments> complex() {
      return Stream.of(
          arguments("1+(7*2-3*(-4+5+6*7/8*(-9+10))-11.348723)", "-15.098723"),
          arguments(
              "54365745-2435/342532(-23424/4234234-4535*12873)*0/-12341/-35345/-23423532/-9999999999996223452346245",
              "54365744.99289118"));
    }

    @ParameterizedTest
    @MethodSource("complex")
    void should_solve_complex_formula(String formula, String result) {
      assertEquals(
          Double.parseDouble(result), calculator.evaluate(Expression.from(formula)).get(), 0.1);
    }
  }
}
