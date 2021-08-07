package stack;

import java.util.*;

/**
 * Given a string s of '(',')'and lowercase English characters.
 *
 * Your task is to remove the minimum number of parentheses ('('or')',in any positions ) so that the resulting parentheses string is valid and return any valid string.
 *
 * Formally, a parentheses string is valid if and only if:
 *
 * It is the empty string, contains only lowercase characters, or
 * It can be written as AB(A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 *
 *
 * Example 1:
 *
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 * Example 2:
 *
 * Input: s = "a)b(c)d"
 * Output: "ab(c)d"
 * Example 3:
 *
 * Input: s = "))(("
 * Output: ""
 * Explanation: An empty string is also valid.
 * */
class ParenthesesString {
  public static String minRemoveToMakeValid(String s) {
    Map<Integer, Boolean> indexToRemove = new HashMap<>();
    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = 0; i < s.length(); i++) {
      char e = s.charAt(i);
      if (e == '(') {
        stack.push(i);
      } else if (e == ')'){
        if (stack.isEmpty()) {
          indexToRemove.put(i, true);
        } else {
          stack.pop();
        }
      }
    }
    while (!stack.isEmpty()) {
      indexToRemove.put(stack.pop(), true);
    }
    StringBuilder r = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      if (notNeeded(i, indexToRemove)) {
        continue;
      }
      r.append(s.charAt(i));
    }
    return r.toString();
  }

  public static boolean notNeeded(Integer c, Map<Integer, Boolean> unneeded) {
    return unneeded.get(c) == null ? false : true;
  }

  public static void main(String[] args) {
    String[] strs = new String[]{
        "lee(t(c)o)de)",
        "a)b(c)d",
        "))(("
    };
    for (String e : strs) {
      System.out.println("\""+ minRemoveToMakeValid(e) + "\"");
    }
  }
}
