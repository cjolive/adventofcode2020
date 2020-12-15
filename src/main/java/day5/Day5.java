package day5;

import java.util.Comparator;
import utils.FileUtils;

public class Day5 {

  public long task1() {

    Long max =
        FileUtils.fileAsStringStream("day5.txt")
            .map(this::calculateSeat)
            .sorted(Comparator.reverseOrder())
            .findFirst()
            .get();

    return max;
  }

  public long task2() {

    Long[] seats =
        FileUtils.fileAsStringStream("day5.txt")
            .map(this::calculateSeat)
            .sorted()
            .toArray(Long[]::new);

    for (int i = 0; i < seats.length; i++) {
      if ((seats[i] + 1) != seats[i + 1]) return seats[i] + 1;
    }
    return -1;
  }

  /**
   * Start by considering the whole range, rows 0 through 127. F means to take the lower half,
   * keeping rows 0 through 63. B means to take the upper half, keeping rows 32 through 63. F means
   * to take the lower half, keeping rows 32 through 47. B means to take the upper half, keeping
   * rows 40 through 47. B keeps rows 44 through 47. F keeps rows 44 through 45. The final F
   * keepsthe lower of the two, row 44.
   *
   * @param position
   * @return
   */
  private long calculateSeat(String position) {
    int minRow = 1;
    int midRow = 64;

    int minSeat = 1;
    int midSeat = 4;
    for (char c : position.toCharArray()) {
      switch (c) {
        case 'B':
          minRow += midRow;
        case 'F':
          midRow /= 2;
          break;
        case 'R':
          minSeat += midSeat;
        case 'L':
          midSeat /= 2;
          break;
      }
    }
    return (minRow - 1) * 8 + (minSeat - 1);
  }
}
