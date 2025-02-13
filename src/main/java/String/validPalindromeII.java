/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package String;

/**
 * Given a string s, return true if the s can be palindrome after deleting at most one character from it.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "aba"
 * Output: true
 * Example 2:
 * <p>
 * Input: s = "abca"
 * Output: true
 * Explanation: You could delete the character 'c'.
 * Example 3:
 * <p>
 * Input: s = "abc"
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 105
 * s consists of lowercase English letters.
 */
public class validPalindromeII {
    /**
     * One will start from 0th Index & another will start from last index.
     * We'll check, if they are equal then continue checking
     * But if they are undequal we can have 2 cases :-
     * Case 1 : Skip e to check whether it's forming an palindrome
     * Case 2 : Skip d to check whether it's forming an plaindrome
     * But still if after deleting one character we are not gettong palindrome return false
     * Otherwise return true
     */
    public static boolean validPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;

        while (i <= j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                // skip left one or skip right one
                return isPalindrome(s, i + 1, j) || isPalindrome(s, i, j - 1);
            }
        }
        return true;
    }

    public static boolean isPalindrome(String s, int i, int j) {
        while (i <= j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
              return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //String test = "aba";
        //String test2 = "abca";
        String test3 = ".,";
        //System.out.println(isPalindrome.isPalindrome(test));
        //System.out.println(isPalindrome.isPalindrome(test2));
        System.out.println(validPalindromeII.validPalindrome(test3));
    }
}
