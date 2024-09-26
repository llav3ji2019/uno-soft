package com.uno.test.file;

import com.uno.test.utils.GroupDataUtils;
import com.uno.test.utils.LineComparator;
import com.uno.test.utils.exception.FileWriteException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Класс для записи результатов работы в файл.
 */
@Slf4j
@RequiredArgsConstructor
public class DataWriter {
  private static final Comparator<List<String>> LIST_COMPARATOR = new LineComparator();

  private final String fileName;
  private int groupCounter = 1;

  /**
   * Записываем данные в файл.
   *
   * @param groups - полученные данные, которые необходимо записать в файл
   */
  public void writeToFile(List<List<String>> groups) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
      int total = groups.size();
      writer.write("Общее кол-во групп = " + total + "\n");

      groups.stream()
          .sorted(LIST_COMPARATOR)
          .map(group -> GroupDataUtils.getGroupData(group, groupCounter++))
          .forEach(groupData -> printCurrentGroupData(groupData, writer));
      writer.flush();
    } catch (IOException e) {
      LOG.error(e.getMessage());
      throw new FileWriteException(e.getMessage());
    }
  }

  private static void printCurrentGroupData(String groupData, BufferedWriter writer) {
    try {
      writer.write(groupData);
    } catch (IOException e) {
      LOG.error(e.getMessage());
      throw new FileWriteException(e.getMessage());
    }
  }
}
