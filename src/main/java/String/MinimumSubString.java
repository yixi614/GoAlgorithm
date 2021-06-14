package String;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MinimumSubString {
  static HashMap<Character, Integer> needed = new HashMap();
  static HashMap<Character, Integer> window = new HashMap();
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
      right++;
      if (needed.containsKey(sc)) {
        window.put(sc, window.getOrDefault(sc, 0) + 1);
      }
      // try to shrink window
      while (left < right && validWindow()) {
        // now sChars[left, right - 1] is a valid window
        int iLength = right - left;
        if (minimumLength >= iLength) {
          minimumLength = iLength;
          resL = left;
          resR = right;
        }
        sc = sChars[left];
        left++;
        if (needed.containsKey(sc)) {
          window.put(sc, window.get(sc) - 1);
        }
      }
    }// end while
    return resL == -1 ? "" : s.substring(resL, resR);
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
    System.out.println(MinimumSubString.minWindow(s, t));
    System.out.println("========");
    s = "a";
    t = "aa";
    System.out.println(MinimumSubString.minWindow(s, t));
    System.out.println("========");
    s = "a";
    t = "b";
    System.out.println(MinimumSubString.minWindow(s, t));
    System.out.println("========");
    s = "aaaaaaaaaaaabbbbbcdd";
    t = "abcdd";
    System.out.println(MinimumSubString.minWindow(s, t));
    System.out.println("========");
    s = "a";
    t = "a";
    System.out.println(MinimumSubString.minWindow(s, t));
    //1231231
  }
}