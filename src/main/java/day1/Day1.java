package day1;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;
import utils.FileUtils;

public class Day1 {

  private static final int EXPECTED = 2020;

  public static void main(String[] args) {
    Set<Integer> numbers =
        FileUtils.fileAsStringStream("day1.txt")
            .map(n -> Integer.valueOf(n))
            .collect(Collectors.toSet());

    System.out.println("Part 1: " + findResultPartOne(numbers));
    System.out.println("Part 2: " + findResultPartTwo(numbers));
  }

  public long task1() {
    Integer[] numbers =
        FileUtils.fileAsStringStream("day1.txt")
            .map(n -> Integer.valueOf(n))
            .toArray(Integer[]::new);
    for (int i = 0; i < numbers.length; i++) {
      for (int j = 0; j < numbers.length; j++) {
        if (i == j) continue;
        if (numbers[i] + numbers[j] == EXPECTED) return numbers[i] * numbers[j];
      }
    }
    return -1;
  }

  public long task2() {
    Integer[] numbers =
        FileUtils.fileAsStringStream("day1.txt")
            .map(n -> Integer.valueOf(n))
            .toArray(Integer[]::new);
    for (int i = 0; i < numbers.length - 2; i++) {
      for (int j = i + 1; j < numbers.length - 1; j++) {
        for (int k = j + 1; k < numbers.length; k++) {
          if (numbers[i] + numbers[j] + numbers[k] == EXPECTED) {
            return numbers[i] * numbers[j] * numbers[k];
          }
        }
      }
    }
    return -1;
  }

  private static String findResultPartOne(Set<Integer> numbers) {
    for (Integer i : numbers) {
      Integer remainder = EXPECTED - i;
      if (numbers.contains(remainder)) {
        return "" + remainder * i;
      }
    }
    return "NOT FOUND";
  }

  private static String findResultPartTwo(Set<Integer> numbers) {
    Integer[] numbersArray =
        numbers.stream().sorted(Comparator.reverseOrder()).toArray(Integer[]::new);
    for (int i = 0; i < numbersArray.length - 2; i++) {
      for (int j = i + 1; j < numbersArray.length - 1; j++) {
        for (int k = j + 1; k < numbersArray.length; k++) {
          if (numbersArray[i] + numbersArray[j] + numbersArray[k] == EXPECTED) {
            return "" + numbersArray[i] * numbersArray[j] * numbersArray[k];
          }
        }
      }
    }
    return "NOT FOUND";
  }
}
