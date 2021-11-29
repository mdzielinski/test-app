package app.closer.testapp.flow.controller;

import app.closer.testapp.data.Formula;
import app.closer.testapp.flow.service.ICalculator;
import app.closer.testapp.flow.validation.BasicFormula;
import app.closer.testapp.util.FormulaExtractor;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Validated
@Slf4j
public class FromulaController {

  private ICalculator calculator;

  @GetMapping("/evaluate/**")
  public String calculate(@BasicFormula HttpServletRequest request) {
    Formula formula = Formula.from(FormulaExtractor.extractFrom(request));
    log.info("UUID: {}, formula: {}", formula.getUuid(), formula);
    return calculator.evaluate(formula).get().toString();
  }

  @PostMapping("/evaluate")
  public String calculate(@RequestBody @BasicFormula Formula formula) {
    log.info("UUID: {}, formula: {}", formula.getUuid(), formula);
    return calculator.evaluate(formula).get().toString();
  }
}
