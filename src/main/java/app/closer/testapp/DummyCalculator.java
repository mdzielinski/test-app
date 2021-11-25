package app.closer.testapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Profile("dummy")
public class DummyCalculator implements ICalculator {

  public String calculate(String equation) {
    String result = null;
    result = switch (equation) {
      case "(2+2)*2" -> "8";
      case "2+2*2" -> "6";
      case "(2+2*2)" -> "6";
      default -> "validation error";
    };

    log.debug("calculated result: " + result);
    return result;
  }
}
