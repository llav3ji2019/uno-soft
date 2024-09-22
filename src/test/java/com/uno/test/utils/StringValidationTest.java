package com.uno.test.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringValidationTest {

  @Test
  @DisplayName("Проверка корректной строки")
  void testCorrectString() {
    String validString = "\"79458129356\";\"79291007012\";\"\";\"79604136336\";\"79724149874\"";
    Assertions.assertTrue(StringValidation.isValid(validString));
  }

  @Test
  @DisplayName("Проверка корректной строки с пустой колонкой")
  void testCorrectStringWithSingleColumn() {
    String validString = "\"\"";
    Assertions.assertTrue(StringValidation.isValid(validString));
  }

  @Test
  @DisplayName("Проверка некорректной строки с дублированным разделителем между колонок")
  void testIncorrectStringWithDuplecatedSeparator() {
    String invalidString = "\"\";;\"\"";
    Assertions.assertFalse(StringValidation.isValid(invalidString));
  }

  @Test
  @DisplayName("Проверка некорректной строки без разделителя колонок")
  void testIncorrectStringWithoutColumnSeparator() {
    String invalidString = "\"189\"\"123\"";
    Assertions.assertFalse(StringValidation.isValid(invalidString));
  }

  @Test
  @DisplayName("Проверка некорректной строки с лишней кавычкой")
  void testIncorrectStringWithSingleExtraQuote() {
    String invalidString = "\"79855053897\"83100000580443402\";\"200000133000191\"";
    Assertions.assertFalse(StringValidation.isValid(invalidString));
  }

  @Test
  @DisplayName("Проверка некорректной строки с двумя лишними кавычками")
  void testIncorrectStringWithTwoExtraQuote() {
    String invalidString = "\"79855053897\"83100000\"580443402\";\"200000133000191\"";
    Assertions.assertFalse(StringValidation.isValid(invalidString));
  }

  @Test
  @DisplayName("Проверка некорректной строки с лишней кавычкой в единственной колонке")
  void testIncorrectStringWithSingleExtraQuoteAndSingleColumn() {
    String invalidString = "\"8383\"200000741652251\"";
    Assertions.assertFalse(StringValidation.isValid(invalidString));
  }
}
