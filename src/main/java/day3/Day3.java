package day3;

import utils.FileUtils;

public class Day3 {

  public long task1() {
    String[] lines = FileUtils.fileAsStringStream("day3.txt").toArray(String[]::new);
    return calculate(lines, 3, 1);
  }

  public long task2() {
    String[] lines = FileUtils.fileAsStringStream("day3.txt").toArray(String[]::new);
    long scenario1 = calculate(lines, 1, 1);
    long scenario2 = calculate(lines, 3, 1);
    long scenario3 = calculate(lines, 5, 1);
    long scenario4 = calculate(lines, 7, 1);
    long scenario5 = calculate(lines, 1, 2);
    return scenario1 * scenario2 * scenario3 * scenario4 * scenario5;
  }

  private long calculate(String[] lines, int right, int down) {
    int lineLength = lines[0].length();
    int pos = right;
    long treeCount = 0;

    for (int i = down; i < lines.length; i += down) {
      String currentLine = lines[i];
      if (currentLine.charAt(pos) == '#') treeCount++;
      pos = (pos + right) % lineLength;
    }

    return treeCount;
  }
}
