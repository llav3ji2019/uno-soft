package com.uno.test.file;

import com.uno.test.utils.LineComparator;
import com.uno.test.utils.exception.FileWriteException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 * Класс для записи результатов работы в файл.
 */
public class DataWriter {
  private static final Comparator<List<String>> LIST_COMPARATOR = new LineComparator();

  private final String fileName;
  private int groupCounter = 1;

  public DataWriter(String fileName) {
    this.fileName = fileName;
  }

  /**
   * Записываем данные в файл.
   *
   * @param result - полученные данные, которые необходимо записать в файл
   */
  public void writeToFile(List<List<String>> result) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
      int total = result.size();
      writer.write("Общее кол-во групп = " + total + "\n");

      result.stream()
          .sorted(LIST_COMPARATOR)
          .map(this::getGroupData)
          .forEach(groupData -> printCurrentGroupData(groupData, writer));
      writer.flush();
    } catch (IOException e) {
      System.err.println(e.getMessage());
      throw new FileWriteException(e.getMessage());
    }
  }

  private static void printCurrentGroupData(String groupData, BufferedWriter writer) {
    try {
      writer.write(groupData);
    } catch (IOException e) {
      System.err.println(e.getMessage());
      throw new FileWriteException(e.getMessage());
    }
  }

  private String getGroupData(List<String> result) {
    var groupData = new StringBuilder("Группа " + groupCounter++ + "\n");
    for (var line : result) {
      groupData.append(line)
          .append("\n");
    }
    groupData.append("\n");
    return groupData.toString();
  }
}
