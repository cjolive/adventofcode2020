import static org.junit.jupiter.api.Assertions.assertEquals;

import day1.Day1;
import day2.Day2;
import org.junit.jupiter.api.Test;

public class Tests {

  @Test
  public void testDay1() {
    Day1 day1 = new Day1();
    assertEquals(1020099, day1.task1());
    assertEquals(49214880, day1.task2());
  }

  @Test
  public void testDay2() {
    Day2 day2 = new Day2();
    assertEquals(477, day2.task1());
    assertEquals(686, day2.task2());
  }
}
