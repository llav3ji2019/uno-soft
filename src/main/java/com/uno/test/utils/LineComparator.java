package com.uno.test.utils;

import java.util.Comparator;
import java.util.List;

/**
 * Позволяет правильно сортировать выходные данные.
 */
public class LineComparator implements Comparator<List<String>> {
  @Override
  public int compare(List<String> o1, List<String> o2) {
    return Integer.compare(o1.size(), o2.size());
  }
}
