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
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Класс для чтения исходных данных из файла.
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
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
          if (StringValidation.isInvalid(curWord)) {
            continue;
          }
          result.add(curWord);
        }
        return result.stream()
            .toList();
      }
    } catch (IOException e) {
      LOG.error("Невозможно прочитать информацию из файла: {}", file.getPath());
      throw new FileReadException(e.getMessage());
    }
  }
}
