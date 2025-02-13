package String;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Minimum Window Substring
 * Given two strings s and t of lengths m and n respectively, return the minimum window
 * substring
 *  of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
 *
 * The testcases will be generated such that the answer is unique.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 * Example 2:
 *
 * Input: s = "a", t = "a"
 * Output: "a"
 * Explanation: The entire string s is the minimum window.
 * Example 3:
 *
 * Input: s = "a", t = "aa"
 * Output: ""
 * Explanation: Both 'a's from t must be included in the window.
 * Since the largest window of s only has one 'a', return empty string.
 *
 *
 * Constraints:
 *
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 105
 * s and t consist of uppercase and lowercase English letters.
 * */
public class MinimumWindowSubString {
  static HashMap<Character, Integer> needed = new HashMap();
  static HashMap<Character, Integer> window = new HashMap();
  // s is the longer string, t is the shorter string
  public static String minWindow(String s, String t) {
    needed.clear();
    window.clear();
    char[] sChars = s.toCharArray();
    char[] tChars = t.toCharArray();
    int minimumLength = s.length();
    // setup needed
    for (char c : tChars) {
      needed.put(c, needed.getOrDefault(c, 0) + 1);
    }
    int resL = -1;
    int resR = -1;
    int left = 0;
    int right = 0;
    while (right < sChars.length) {
      // expand window
      char sc = sChars[right];
      if (needed.containsKey(sc)) {
        window.put(sc, window.getOrDefault(sc, 0) + 1);
      }
      // try to shrink window
      // valid window means that window string covers more or equal to the needed string characters
      // so we shrink the window to find the minimum window
      while (left <= right && validWindow()) {
        // now sChars[left, right] is a valid window
        int iLength = right - left + 1;
        if (minimumLength >= iLength) {
          minimumLength = iLength;
          resL = left;
          resR = right;
        }
        // maintain the window content
        sc = sChars[left];
        if (needed.containsKey(sc)) {
          window.put(sc, window.get(sc) - 1);
        }
        left++;
      }
      right++;
    }// end while
    return resL == -1 ? "" : s.substring(resL, resR + 1);
  }

  private static boolean validWindow() {
    Iterator iter = needed.entrySet().iterator();
    while (iter.hasNext()) {
      Map.Entry entry = (Map.Entry) iter.next();
      Character key = (Character) entry.getKey();
      Integer val = (Integer) entry.getValue();
      if (window.getOrDefault(key, 0) < val) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    String s, t;
    s = "ADOBECODEBANC";
    t = "ABC";
    System.out.println(MinimumWindowSubString.minWindow(s, t));
    System.out.println("========");
    s = "a";
    t = "aa";
    System.out.println(MinimumWindowSubString.minWindow(s, t));
    System.out.println("========");
    s = "a";
    t = "b";
    System.out.println(MinimumWindowSubString.minWindow(s, t));
    System.out.println("========");
    s = "aaaaaaaaaaaabbbbbcdd";
    t = "abcdd";
    System.out.println(MinimumWindowSubString.minWindow(s, t));
    System.out.println("========");
    s = "a";
    t = "a";
    System.out.println(MinimumWindowSubString.minWindow(s, t));
    //1231231
  }
}