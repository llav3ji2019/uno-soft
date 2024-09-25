package com.uno.test.utils;

/**
 * Класс для валидации строки.
 */
public class StringValidation {
  private static final char ELEMENT_QUOTE = '"';
  private static final char COLUMN_SEPARATOR = ';';

  /**
   * Метод Валидации строки.
   *
   * @param s - исходная строка
   * @return если строка соответствует требованиям задачи, то true, а иначе - false
   */
  public static boolean isValid(String s) {
    int quoteCounter = 0;
    int separatorCounter = 0;
    for (int i = 0; i < s.length(); i++) {
      char currentSymbol = s.charAt(i);
      if (i == 0 && (currentSymbol != ELEMENT_QUOTE && currentSymbol != COLUMN_SEPARATOR)) {
        return false;
      }
      if (currentSymbol == COLUMN_SEPARATOR
          && ((i > 0 && s.charAt(i - 1) != ELEMENT_QUOTE) || (i < s.length() - 1 && s.charAt(i + 1) != ELEMENT_QUOTE))) {
        return false;
      }
      if (currentSymbol == ELEMENT_QUOTE) {
        quoteCounter++;
        continue;
      }
      if (currentSymbol == COLUMN_SEPARATOR) {
        separatorCounter++;
      }
    }
    return quoteCounter % 2 == 0 && quoteCounter / 2 - 1 == separatorCounter;
  }

  private StringValidation() {

  }
}
