package com.uno.test.processor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import lombok.Getter;

/**
 * Класс по группировке исходных строк по столбцам.
 */
@Getter
public class GroupingProcessor {
  private static final String COLUMN_SEPARATOR = ";";

  private final List<List<String>> groupedData;

  public GroupingProcessor(List<String> src) {
    groupedData = getGroupedData(src);
  }

  private List<List<String>> getGroupedData(List<String> src) {
    List<List<String>> result = new ArrayList<>();
    List<Map<String, Integer>> columnGroupStatistics = new ArrayList<>();
    for (String line : src) {
      List<String> numbers = getSeparatedNumbers(line);
      TreeSet<Integer> groupsToMergeIds = new TreeSet<>();

      for (int i = 0; i < numbers.size(); i++) {
        if (columnGroupStatistics.size() == i) {
          columnGroupStatistics.add(new HashMap<>());
        }
        String currentColumnValue = numbers.get(i);
        if (isEmpty(currentColumnValue)) {
          continue;
        }
        Integer groupIndex = columnGroupStatistics.get(i)
            .get(currentColumnValue);
        if (groupIndex != null) {
          groupsToMergeIds.add(groupIndex);
        }
      }
      int indexInResult = getMainGroupIndex(groupsToMergeIds, result);
      if (groupsToMergeIds.isEmpty()) {
        result.add(new ArrayList<>());
      }
      result.get(indexInResult).add(line);

      updateColumnGroupStatistics(numbers, columnGroupStatistics, indexInResult);

      mergeGroups(groupsToMergeIds, indexInResult, result);
    }
    return result.stream()
        .filter(list -> list.size() > 1)
        .toList();
  }

  private static boolean isEmpty(String currentColumnValue) {
    return currentColumnValue.equals("\"\"") || currentColumnValue.isEmpty();
  }

  private static void mergeGroups(TreeSet<Integer> groupsToMergeIds, int indexInResult,
                                List<List<String>> result) {
    for (var groupToMergeId : groupsToMergeIds) {
      if (groupToMergeId == indexInResult) {
        continue;
      }
      result.get(indexInResult)
          .addAll(result.get(groupToMergeId));
      result.set(groupToMergeId, new ArrayList<>());
    }
  }

  private static void updateColumnGroupStatistics(List<String> numbers,
                                List<Map<String, Integer>> columnGroupStatistics,
                                int indexInResult) {
    for (int i = 0; i < numbers.size(); i++) {
      columnGroupStatistics.get(i)
          .put(numbers.get(i), indexInResult);
    }
  }

  private static int getMainGroupIndex(TreeSet<Integer> groupsToMergeIds, List<List<String>> result) {
    return groupsToMergeIds.isEmpty() ? result.size() : groupsToMergeIds.first();
  }

  private static List<String> getSeparatedNumbers(String line) {
    return Arrays.stream(line.split(COLUMN_SEPARATOR))
        .toList();
  }
}
