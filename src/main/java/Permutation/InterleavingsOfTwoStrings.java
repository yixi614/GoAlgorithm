package Permutation;

/**
 * Given two strings str1 and str2,
 * write a function that prints all interleavings of the given two strings.
 * You may assume that all characters in both strings are different

 Example:

 Input: str1 = "AB",  str2 = "CD"
 Output:
 ABCD
 ACBD
 ACDB
 CABD
 CADB
 CDAB

 Input: str1 = "AB",  str2 = "C"
 Output:
 ABC
 ACB
 CAB
 An interleaved string of given two strings preserves the order of characters in individual strings.
 For example, in all the interleavings of above first example,
 ‘A’ comes before ‘B’ and ‘C’ comes before ‘D’.
 * */
public class InterleavingsOfTwoStrings {
//  To print all interleavings,
//  we can first fix the first character of str1[0..m-1] in output string,
//  and recursively call for str1[1..m-1] and str2[0..n-1].
//  And then we can fix the first character of str2[0..n-1] and
//  recursively call for str1[0..m-1] and str2[1..n-1].
//  # Python program to print all interleavings of given two strings
//
//# Utility function
//  def toString(List):
//          return "".join(List)
//
//# The main function that recursively prints all interleavings.
//          # The variable iStr is used to store all interleavings (or output
//# strings) one by one.
//          # i is used to pass next available place in iStr
//  def printIlsRecur(str1, str2, iStr, m, n, i):
//
//          # Base case: If all characters of str1 and str2 have been
//    # included in output string, then print the output string
//    if m==0 and n==0:
//  print toString(iStr)
//
//    # If some characters of str1 are left to be included, then
//    # include the first character from the remaining characters
//    # and recur for rest
//    if m != 0:
//  iStr[i] = str1[0]
//  printIlsRecur(str1[1:], str2, iStr, m-1, n, i+1)
//
//    # If some characters of str2 are left to be included, then
//    # include the first character from the remaining characters
//    # and recur for rest
//    if n != 0:
//  iStr[i] = str2[0]
//  printIlsRecur(str1, str2[1:], iStr, m, n-1, i+1)
//
//# Allocates memory for output string and uses printIlsRecur()
//# for printing all interleavings
//  def printIls(str1, str2, m, n):
//  iStr = [''] * (m+n)
//
//          # print all interleavings using printIlsRecur()
//  printIlsRecur(str1, str2, iStr, m, n, 0)
//
//# Driver program to test the above function
//          str1 = "AB"
//  str2 = "CD"
//  printIls(str1, str2, len(str1), len(str2))

}
