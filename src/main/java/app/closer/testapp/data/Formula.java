package app.closer.testapp.data;

import java.util.UUID;
import org.springframework.validation.annotation.Validated;

@Validated
public final class Formula {

  private final String body;
  private final UUID uuid;

  private Formula(String body) {
    this.uuid = UUID.randomUUID();
    this.body = sanitize(body);
  }

  private static String sanitize(String body) {
    return removeWhitespaceCodes(body);
  }

  private static String removeWhitespaceCodes(String s) {
    return s.replaceAll("%20", "");
  }

  public static Formula from(String formula) {
    return new Formula(formula);
  }

  public String getBody() {
    return body;
  }

  public UUID getUuid() {
    return uuid;
  }

  @Override
  public String toString() {
    return "\"" + body + "\"";
  }
}
