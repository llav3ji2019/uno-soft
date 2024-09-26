package com.uno.test.utils;

import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GroupDataUtils {
  public static String getGroupData(List<String> result, int counter) {
    var groupData = new StringBuilder("Группа " + counter + "\n");
    for (var line : result) {
      groupData.append(line)
          .append("\n");
    }
    groupData.append("\n");
    return groupData.toString();
  }
}
