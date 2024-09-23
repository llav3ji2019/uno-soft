package com.uno.test.file;

import com.uno.test.utils.StringValidation;
import com.uno.test.utils.exception.FileReadException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Класс для чтения исходных данных из файла.
 */
public class DataReader {
  /**
   * Чтение исходных данных из файла.
   *
   * @param file - файл, содержащий исходные данные
   * @return полученные из файла исходные данные
   */
  public static List<String> readData(File file) {
    try (FileReader reader = new FileReader(file)) {
      try (Scanner in = new Scanner(reader)) {
        final Set<String> result = new HashSet<>();
        while (in.hasNextLine()) {
          String curWord = in.nextLine();
          if (!StringValidation.isValid(curWord)) {
            continue;
          }
          result.add(curWord);
        }
        return result.stream()
            .toList();
      }
    } catch (IOException e) {
      System.err.println("Невозможно прочитать информацию из файла: " + file);
      throw new FileReadException(e.getMessage());
    }
  }

  private DataReader() {

  }
}
