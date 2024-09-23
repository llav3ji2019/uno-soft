package com.uno.test.utils;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LineComparatorTest {

  @Test
  @DisplayName("Сравнение списков с разным количеством элементов")
  void testComparisonLineComparator() {
    List<String> firstLine = List.of("1", "2", "3", "4", "5", "6", "7", "8");
    List<String> secondLine = List.of("14", "12", "11", "18");

    Assertions.assertTrue(new LineComparator().compare(firstLine, secondLine) < 0);
    Assertions.assertTrue(new LineComparator().compare(secondLine, firstLine) > 0);
  }

  @Test
  @DisplayName("Сравнение списков с одинаковым количеством элементов")
  void testComparisonOfLinesWithSameSizes() {
    List<String> firstLine = List.of("1", "2", "3", "4");
    List<String> secondLine = List.of("11", "21", "31", "41");

    Assertions.assertEquals(0, new LineComparator().compare(firstLine, secondLine));
  }
}
