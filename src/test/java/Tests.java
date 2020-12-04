import static org.junit.jupiter.api.Assertions.assertEquals;

import day1.Day1;
import day2.Day2;
import day3.Day3;
import day4.Day4;
import org.junit.jupiter.api.Test;

public class Tests {

  @Test
  public void testDay1() {
    Day1 day = new Day1();
    assertEquals(1020099, day.task1());
    assertEquals(49214880, day.task2());
  }

  @Test
  public void testDay2() {
    Day2 day = new Day2();
    assertEquals(477, day.task1());
    assertEquals(686, day.task2());
  }

  @Test
  public void testDay3() {
    Day3 day = new Day3();
    assertEquals(292, day.task1());
    assertEquals(9354744432L, day.task2());
  }

  @Test
  public void testDay4() {
    Day4 day = new Day4();
    assertEquals(226, day.task1());
    assertEquals(160, day.task2());
  }
}
