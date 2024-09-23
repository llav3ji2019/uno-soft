package com.uno.test;

import com.uno.test.file.DataReader;
import com.uno.test.file.DataWriter;
import com.uno.test.processor.GroupingProcessor;
import com.uno.test.utils.exception.FileNameNotFound;
import java.io.File;
import java.util.List;

public class Main {
  private static final String OUTPUT_FILE_NAME = "result.txt";

  public static void main(String[] args) {
    if (args.length < 1) {
      System.err.println("Неправильное название файла");
      throw new FileNameNotFound("Неправильное название файла");
    }
    String fileName = args[0];

    List<String> data = DataReader.readData(new File(fileName));

    List<List<String>> groupedData = new GroupingProcessor(data).getGroupedData();

    var writer = new DataWriter(OUTPUT_FILE_NAME);
    writer.writeToFile(groupedData);
  }
}
