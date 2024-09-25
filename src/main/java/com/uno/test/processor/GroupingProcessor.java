package com.uno.test.processor;

import com.uno.test.utils.ColumnElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

/**
 * Класс по группировке исходных строк по столбцам.
 */
public class GroupingProcessor {
  private static final String COLUMN_SEPARATOR = ";";

  private final List<List<String>> groupedData;

  public GroupingProcessor(List<String> src) {
    groupedData = getGroupedData(src);
  }

  public List<List<String>> getGroupedData() {
    return groupedData;
  }

  private List<List<String>> getGroupedData(List<String> src) {
    List<List<String>> result = new ArrayList<>();
    List<Map<String, Integer>> columnGroupStatistics = new ArrayList<>();
    Map<Integer, Integer> mergedColumnGroupStatistics = new HashMap<>();
    for (String line : src) {
      List<String> numbers = getSeparatedNumbers(line);
      TreeSet<Integer> groupsToMergeIds = new TreeSet<>();
      List<ColumnElement> newColumnValues = new ArrayList<>();

      for (int i = 0; i < numbers.size(); i++) {
        String currentColumnValue = numbers.get(i);
        if (columnGroupStatistics.size() == i) {
          columnGroupStatistics.add(new HashMap<>());
        }
        if (currentColumnValue.equals("\"\"") || currentColumnValue.isEmpty()) {
          continue;
        }
        Integer groupIndex = columnGroupStatistics.get(i)
            .get(currentColumnValue);
        if (groupIndex == null) {
          newColumnValues.add(new ColumnElement(currentColumnValue, i));
        } else {
          groupsToMergeIds.add(groupIndex);
        }
      }
      int indexInResult = getIndexInResult(groupsToMergeIds, result, mergedColumnGroupStatistics);
      if (groupsToMergeIds.isEmpty()) {
        result.add(new ArrayList<>());
      }
      result.get(indexInResult).add(line);

      for (var element : newColumnValues) {
        columnGroupStatistics.get(element.index())
            .put(element.value(), indexInResult);
      }
      for (var groupToMergeId : groupsToMergeIds) {
        if (groupToMergeId == indexInResult || mergedColumnGroupStatistics.containsKey(groupToMergeId)) {
          continue;
        }
        result.get(indexInResult)
            .addAll(result.get(groupToMergeId));
        result.set(groupToMergeId, null);
        mergedColumnGroupStatistics.put(groupToMergeId, indexInResult);
        if (mergedColumnGroupStatistics.containsValue(groupToMergeId)) {
          for (var entry : mergedColumnGroupStatistics.entrySet()) {
            if (entry.getValue().equals(groupToMergeId)) {
              entry.setValue(indexInResult);
            }
          }
        }
      }
    }
    return result.stream()
        .filter(Objects::nonNull)
        .filter(list -> list.size() > 1)
        .toList();
  }

  private static int getIndexInResult(TreeSet<Integer> groupsToMergeIds, List<List<String>> result,
                                      Map<Integer, Integer> mergedColumnGroupStatistics) {
    int mainGroupIndex = groupsToMergeIds.isEmpty() ? result.size() : groupsToMergeIds.first();
    return mergedColumnGroupStatistics.getOrDefault(mainGroupIndex, mainGroupIndex);
  }

  private static List<String> getSeparatedNumbers(String line) {
    return Arrays.stream(line.split(COLUMN_SEPARATOR))
        .toList();
  }
}
