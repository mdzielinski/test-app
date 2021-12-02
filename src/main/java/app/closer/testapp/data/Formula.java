package app.closer.testapp.data;

import java.util.UUID;
import org.springframework.validation.annotation.Validated;

@Validated
public final class Formula {

  private final String body;
  private final UUID uuid;

  public static Formula from(String formula) {
    return new Formula(formula);
  }

  private Formula(String body) {
    this.uuid = UUID.fromString(body);
    this.body = sanitize(body);
  }

  private static String sanitize(String body) {
    return removeURLWhitespaceCodes(body);
  }

  private static String removeURLWhitespaceCodes(String s) {
    return s.replaceAll("%20", "");
  }

  @Override
  public String toString() {
    return "\"" + body + "\"";
  }

  public String getBody() {
    return body;
  }

  public UUID getUuid() {
    return uuid;
  }
}
