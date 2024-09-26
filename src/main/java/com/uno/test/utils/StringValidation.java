package com.uno.test.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Класс для валидации строки.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringValidation {
  private static final char ELEMENT_QUOTE = '"';
  private static final char COLUMN_SEPARATOR = ';';

  /**
   * Метод для валидации строки.
   *
   * @param s - исходная строка
   * @return если строка соответствует требованиям задачи, то true, а иначе - false
   */
  public static boolean isValid(String s) {
    int quoteCounter = 0;
    for (int i = 0; i < s.length(); i++) {
      char currentSymbol = s.charAt(i);
      if (currentSymbol == ELEMENT_QUOTE) {
        if (quoteCounter == 0) {
          quoteCounter++;
          continue;
        }
        if (quoteCounter == 1) {
          if (i < s.length() - 1 && s.charAt(i + 1) != COLUMN_SEPARATOR) {
            return false;
          }
          if (i == s.length() - 1) {
            return true;
          }
          i++;
          quoteCounter = 0;
        }
      } else if (currentSymbol == COLUMN_SEPARATOR && (quoteCounter != 2 && quoteCounter != 0)) {
          return false;
      }
    }
    return quoteCounter % 2 == 0;
  }

  public static boolean isInvalid(String s) {
    return !isValid(s);
  }
}
