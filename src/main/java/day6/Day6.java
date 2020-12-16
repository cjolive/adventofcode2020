package day6;

import java.util.*;
import java.util.stream.Collectors;
import utils.FileUtils;

public class Day6 {

  public long task1() {
    List<String> lines = FileUtils.fileAsStringStream("day6.txt").collect(Collectors.toList());

    long sum = 0;
    Set<Character> characterSet = new HashSet<>();

    for (String line : lines) {
      if (line.isEmpty()) {
        sum += characterSet.size();
        characterSet.clear();
      } else {
        for (char c : line.toCharArray()) {
          characterSet.add(c);
        }
      }
    }

    if (!characterSet.isEmpty()) sum += characterSet.size();

    return sum;
  }

  public long task2() {
    List<String> lines = FileUtils.fileAsStringStream("day6.txt").collect(Collectors.toList());

    long sum = 0;
    int groupSize = 0;

    List<Character> answers = new ArrayList<>();
    for (String line : lines) {
      if (line.isEmpty()) {
        sum += findCommonAnswers(answers, groupSize);
        answers.clear();
        groupSize = 0;
      } else {
        for (char c : line.toCharArray()) {
          answers.add(c);
        }
        groupSize++;
      }
    }

    if (!answers.isEmpty()) sum += findCommonAnswers(answers, groupSize);

    return sum;
  }

  private long findCommonAnswers(List<Character> answers, final int groupSize) {
    Map<Character, Long> characterMap =
        answers.stream().collect(Collectors.groupingBy(c -> c, Collectors.counting()));
    return characterMap.entrySet().stream().filter(c -> c.getValue() == groupSize).count();
  }
}
