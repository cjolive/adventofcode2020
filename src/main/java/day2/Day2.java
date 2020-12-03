package day2;

import lombok.Getter;
import utils.FileUtils;

/**
 * 1-3 a: abcde 1-3 b: cdefg 2-9 c: ccccccccc Each line gives the password policy and then the
 * password. The password policy indicates the lowest and highest number of times a given letter
 * must appear for the password to be valid. For example, 1-3 a means that the password must contain
 * a at least 1 time and at most 3 times.
 */
public class Day2 {

  public long task1() {
    long count =
        FileUtils.fileAsStringStream("day2.txt")
            .map(p -> new Password(p))
            .filter(pw -> pw.isValid())
            .count();
    return count;
  }

  public long task2() {
    long count =
        FileUtils.fileAsStringStream("day2.txt")
            .map(p -> new Password(p))
            .filter(pw -> pw.isValid2())
            .count();
    return count;
  }

  static class Password {
    private String criteria;
    private String password;

    // 1-3 a: abcde
    // TODO replace multiple splits with regex
    public Password(String criteriaAndPassword) {
      String[] parts = criteriaAndPassword.split(":");
      this.criteria = parts[0].trim();
      this.password = parts[1].trim();
    }

    public boolean isValid() {
      NumberCharHolder holder = new NumberCharHolder(this.criteria);
      int appearances = 0;
      for (char c : password.toCharArray()) {
        if (c == holder.getLetter()) appearances++;
      }
      return appearances >= holder.getNum1() && appearances <= holder.getNum2();
    }

    public boolean isValid2() {
      NumberCharHolder holder = new NumberCharHolder(this.criteria);
      char a = this.password.charAt(holder.getNum1() - 1);
      char b = this.password.charAt(holder.getNum2() - 1);
      return a == holder.getLetter() ^ b == holder.getLetter();
    }

    @Getter
    class NumberCharHolder {

      private int num1;
      private int num2;
      private char letter;

      NumberCharHolder(String s) {
        String[] criteriaAndChar = s.split(" ");
        String[] numbers = criteriaAndChar[0].split("-");
        num1 = Integer.valueOf(numbers[0]);
        num2 = Integer.valueOf(numbers[1]);
        letter = criteriaAndChar[1].trim().charAt(0);
      }
    }
  }
}
