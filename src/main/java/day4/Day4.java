package day4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import utils.FileUtils;

public class Day4 {

  final Set<String> REQUIRED_ELEMENTS = Set.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");

  public int task1() {
    return filterPassports(false);
  }

  public int task2() {
    return filterPassports(true);
  }

  private int filterPassports(boolean validate) {
    List<String> lines = FileUtils.fileAsStringStream("day4.txt").collect(Collectors.toList());

    int validCount = 0;
    Set<String> currentRecord = new HashSet<>();

    for (String line : lines) {
      if (line.isEmpty()) {
        if (currentRecord.containsAll(REQUIRED_ELEMENTS)) validCount++;
        currentRecord.clear();
      } else {
        currentRecord.addAll(handleLine(line, validate));
      }
    }

    if (!currentRecord.isEmpty() && currentRecord.containsAll(REQUIRED_ELEMENTS)) validCount++;

    return validCount;
  }

  private Set<String> handleLine(String line, boolean validate) {
    if (validate)
      return Arrays.stream(line.split(" "))
          .filter(this::isValid)
          .map(s -> s.split(":")[0])
          .collect(Collectors.toSet());
    return Arrays.stream(line.split(" ")).map(s -> s.split(":")[0]).collect(Collectors.toSet());
  }

  private boolean isValid(String s) {
    String[] parts = s.split(":");
    switch (parts[0]) {
      case "byr":
        return validateYear(parts[1], 1920, 2002);
      case "iyr":
        return validateYear(parts[1], 2010, 2020);
      case "eyr":
        return validateYear(parts[1], 2020, 2030);
      case "hgt":
        return validateHeight(parts[1]);
      case "hcl":
        return Pattern.matches("^#(?:[0-9a-f]){6}$", parts[1]);
      case "ecl":
        return Pattern.matches("amb|blu|brn|gry|grn|hzl|oth", parts[1]);
      case "pid":
        return Pattern.matches("^[0-9]{9}$", parts[1]);
      case "cid":
        return true;
      default:
        return false;
    }
  }

  private boolean validateYear(String s, int min, int max) {
    if (s.length() != 4) return false;
    try {
      Integer i = Integer.valueOf(s);
      return i >= min && i <= max;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  private boolean validateHeight(String s) {
    try {
      Integer i;
      if (s.length() == 5 && s.endsWith("cm")) {
        i = Integer.valueOf(s.substring(0, 3));
        return i >= 150 && i <= 193;
      } else if (s.length() == 4 && s.endsWith("in")) {
        i = Integer.valueOf(s.substring(0, 2));
        return i >= 59 && i <= 76;
      }
      return false;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}
