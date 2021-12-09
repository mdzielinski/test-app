package app.closer.testapp.util;

import javax.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FormulaExtractor {

  public static String extractFrom(HttpServletRequest request) {
    return request.getRequestURI().replaceAll("[A-za-z/]+/", "");
  }
}
