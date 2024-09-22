package com.uno.test;

import com.uno.test.file.DataReader;
import com.uno.test.file.DataWriter;
import com.uno.test.processor.GroupingProcessor;
import java.io.File;
import java.util.List;

public class Main {
  private static final String OUTPUT_FILE_NAME = "result.txt";

  public static void main(String[] args) {
    if (args.length < 1) {
      System.err.println("Error: Invalid file name");
      return;
    }
    String fileName = args[0];

    List<String> data = DataReader.readData(new File(fileName));

    List<List<String>> groupedData = new GroupingProcessor(data).getGroupedData();

    var writer = new DataWriter(OUTPUT_FILE_NAME);
    writer.writeToFile(groupedData);
  }
}
