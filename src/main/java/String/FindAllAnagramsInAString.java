package String;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

 Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

 The order of output does not matter.

 Example 1:

 Input:
 s: "cbaebabacd" p: "abc"

 Output:
 [0, 6]

 Explanation:
 The substring with start index = 0 is "cba", which is an anagram of "abc".
 The substring with start index = 6 is "bac", which is an anagram of "abc".
 * */
public class FindAllAnagramsInAString {
  public static List<Integer> findAnagrams(String s, String p) {
    List<Integer> r = new ArrayList<Integer>();
    int pHashCode = hashCode(p);
    int step = p.length();
    for(int i = 0; i <= s.length()-step; i++) {
      if(pHashCode == hashCode(s.substring(i, i+step))){
        r.add(i);
      }
    }
    return r;
  }
  public static int hashCode(String p) {
    int hash = 0;
    for(int i = 0; i < p.length(); i++) {
      hash += 1<<(p.charAt(i) - 'a');
    }
    return hash;
  }

  public static void main(String[] args) {
    String s = "cbaebabacd";
    String p = "abc";
    List<Integer> r = FindAllAnagramsInAString.findAnagrams(s, p);
    System.out.println(r);
  }
}
