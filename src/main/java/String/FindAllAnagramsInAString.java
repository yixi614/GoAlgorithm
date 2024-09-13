package String;

import java.util.*;

/**
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

 Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

 n Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 The order of output does not matter.

 Example 1:

 Input:
 s: "cbaebabacd" p: "abc"

 Output:
 [0, 6]

 Explanation:
 The substring with start index = 0 is "cba", which is an anagram of "abc".
 The substring with start index = 6 is "bac", which is an anagram of "abc".

 Example 2:

 Input: s = "abab", p = "ab"
 Output: [0,1,2]
 Explanation:
 The substring with start index = 0 is "ab", which is an anagram of "ab".
 The substring with start index = 1 is "ba", which is an anagram of "ab".
 The substring with start index = 2 is "ab", which is an anagram of "ab".
 * */
public class FindAllAnagramsInAString {
//  public static List<Integer> findAnagrams(String s, String p) {
//    List<Integer> r = new ArrayList<Integer>();
//    int pHashCode = hashCode(p);
//    int step = p.length();
//    for(int i = 0; i <= s.length()-step; i++) {
//      if(pHashCode == hashCode(s.substring(i, i+step))){
//        r.add(i);
//      }
//    }
//    return r;
//  }
//  public static int hashCode(String p) {
//    int hash = 0;
//    for(int i = 0; i < p.length(); i++) {
//      hash += 1<<(p.charAt(i) - 'a');
//    }
//    return hash;
//  }

  public static boolean isAnagram(char[] s, char t[]) {
    int [] arr = new int[26];
    for(char ch: s)
      arr[ch - 97] += 1;
    for (char ch: t)
      arr[ch - 97] -= 1;
    for (int count: arr)
      if (count != 0) return false;
    return true;
  }
  public static List<Integer> findAnagrams(String s, String p) {
    int finish = p.length();
    List<Integer> indexList= new ArrayList<>();
    if (s.length() < p.length())
      return indexList;
    char[] pStr = p.toCharArray();
    char[] sStr = s.toCharArray();
    while (finish <= s.length())
    {
      if (isAnagram(Arrays.copyOfRange(sStr,finish - p.length(),finish), pStr))
        indexList.add(finish - p.length());
      finish++;
    }
    return indexList;
  }

  public static void main(String[] args) {
    String s = "cbaebabacd";
    String p = "abc";
    List<Integer> r = FindAllAnagramsInAString.findAnagrams(s, p);
    System.out.println(r);
  }
}
