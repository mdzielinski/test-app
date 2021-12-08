package app.closer.testapp.util;

import java.util.regex.Pattern;

public class RegexHelper {

  public static boolean patternMatches(String regex, String word) {
    return Pattern.compile(RegexHelper.wordRegexOf(regex)).matcher(word).matches();
  }

  private static String wordRegexOf(String word) {
    return "^[" + word + "]$";
  }
}
