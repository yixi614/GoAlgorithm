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

public class isPalindrome {
  public static boolean isPalindrome(String s) {
    char[] tokens = s.toLowerCase().toCharArray();
    char[] filteredTokens = new char[tokens.length];
    int count = 0;
    for (int i = 0; i < tokens.length; i++) {
      if (isDigit(tokens[i])) {
        filteredTokens[count] = tokens[i];
        count++;
      }
    }
    int i, j;
    if (count == 0 || count == 1) {
      return true;
    }
    if (count % 2 == 0) {
      i = count / 2 - 1;
      j = i + 1;
    } else {
      i = count / 2 - 1;
      j = i + 2;
    }
    return helper(filteredTokens, i, j, count);
  }

  public static boolean helper(char[] tokens, int i, int j, int size) {
    while (i > 0 && j < size - 1) {
      if (tokens[i] != tokens[j]) {
        return false;
      }
      i--;
      j++;
    }
    return tokens[i] == tokens[j];
  }

  public static boolean isDigit(char c) {
    return c >= 'a' && c <='z' ||
        c >= 'A' && c <= 'Z' ||
        c >= '0' && c <= '9';
  }

  public static void main(String[] args) {
    //String test = "A man, a plan, a canal: Panama";
    //String test2 = "a";
    String test3 = ".,";
    //System.out.println(isPalindrome.isPalindrome(test));
    //System.out.println(isPalindrome.isPalindrome(test2));
    System.out.println(isPalindrome.isPalindrome(test3));
  }
}
