package app.closer.testapp.flow.validation;

import static app.closer.testapp.flow.validation.Validator.isValid;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ValidatorTest {

  @Nested
  class well_formed_operators {
    static Stream<Arguments> wellFormedOperators() {
      return Stream.of(
          arguments("1+1+6"),
          arguments("1+1+6"),
          arguments("-1+1+6"),
          arguments("1+-1+6"),
          arguments("-1-1+-6"),
          arguments("-1-1+6"),
          arguments("-1-1+6."),
          arguments("1+1+1+1+1"),
          arguments("1+1*1+1+1"),
          arguments("1+1*-1+1+1"),
          arguments("-1*-1/-1+-1-1/-1"),
          arguments("1+1+1+1+1"),
          arguments("1+1/1+1+1"),
          arguments("1+1-/1+1+1"),
          arguments("1142131.*.12314213"));
    }

    @ParameterizedTest
    @MethodSource("wellFormedOperators")
    void should_accept_because_operators_fine(String formula) {
      assertTrue(isValid(formula));
    }
  }

  @Nested
  class malformed_numbers {
    static Stream<Arguments> malformedNumbers() {
      return Stream.of(
          arguments("1+1,1+1"),
          arguments("1+1283492e-234+1"),
          arguments("1.1+1,1"),
          arguments("1,1+1.1"),
          arguments("1,1+.1,1"));
    }

    @ParameterizedTest
    @MethodSource("malformedNumbers")
    void should_reject_because_numbers_malformed(String formula) {
      assertFalse(isValid(formula));
    }
  }
}
