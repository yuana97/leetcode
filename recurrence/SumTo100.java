package recurrence;

/**
 * Sum To 100 (Uber/Facebook phone screen)
 * 
 * Question:
 * Add + or - between any two digits of 123456789 such that the resulting expression evaluates
 * to 100. Return a list of strings 1+/-2+/-3... which are solutions.
 * 
 * Solution:
 * Recursive backtracking. Recurse on adding +, - or nothing and repeating the problem on the suffix.
 * 
 * Script:
 * - Let me give a quick overview of the problem.
 * - First, let's generalize the problem to any target (instead of just 100),
 * and any string of digits (instead of just 123456789). To solve the problem,
 * we would simply call sumTo("123456789", 100).
 * - We can 
 * 
 */

import java.util.*;

public class SumTo100 {
  private static final String DIGITS = "123456789";

  public static List<String> sumTo(String digits, int target) {
    List<String> result = new ArrayList<>();
    generate(digits, 0, 0, target, new StringBuilder(), result);
    return result;
  }

  // given a prefix (encoded by start, sum, path), generate all paths which are solutions
  private static void generate(String digits, int start, int sum, int target, StringBuilder path, List<String> result) {
    // processed all digits => done
    if (start == digits.length()) {
      if (sum == target) {
        result.add(path.toString());
      }
      return;
    }

    int len = path.length();
    int num = 0;
    // add/subtract the next block of length 1, 2, 3, ... length-start 
    for (int i = start; i < digits.length(); i++) {
      num = 10 * num + digits.charAt(i) - '0';

      // try adding num
      if (start != 0) path.append('+');
      path.append(num);
      generate(digits, i+1, sum+num, target, path, result);
      path.setLength(len);

      // try subtracting num
      path.append('-');
      path.append(num);
      generate(digits, i+1, sum-num, target, path, result);
      path.setLength(len);
    }
  }

  // unit test
  public static void main(String[] args) {
    test(100);
    test(9);
  }

  // log outputs
  public static void test(int target) {
    System.out.println(String.format("Sum to %d -------", target));
    int count = 1;
    for (String s : sumTo(DIGITS, target)) {
      System.out.println(count + ": " + s);
      count++;
    }
  }
}