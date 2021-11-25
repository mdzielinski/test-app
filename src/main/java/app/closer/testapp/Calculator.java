package app.closer.testapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Profile("prod")
public class Calculator implements ICalculator {

  public String calculate(String equation){
    String result = null;
    log.debug("calculated result: " + result);
    return evaluate(equation);

  }

  private String evaluate(String equation) {
    return null;
  }
}
