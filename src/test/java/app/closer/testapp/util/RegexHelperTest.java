package app.closer.testapp.util;

import static app.closer.testapp.util.RegexHelper.patternMatches;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import app.closer.testapp.data.symbol.Symbol;
import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RegexHelperTest {

  @Nested
  class number_matching {
    static Stream<Arguments> symbols() {
      return Stream.of(arguments("+"), arguments("-"), arguments("*"), arguments("/"));
    }

    @ParameterizedTest
    @MethodSource("symbols")
    void should_match(String number) {
      assertTrue(patternMatches(Symbol.OPERATOR_SYMBOLS, number));
    }
  }
}
