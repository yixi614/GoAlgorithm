package Permutation;

/**
 * Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
 * <p>
 * An interleaving of two strings s and t is a configuration where s and t are divided into n and m
 * substrings
 * respectively, such that:
 * <p>
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
 * Note: a + b is the concatenation of strings a and b.
 * <p>
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true
 * Explanation: One way to obtain s3 is:
 * Split s1 into s1 = "aa" + "bc" + "c", and s2 into s2 = "dbbc" + "a".
 * Interleaving the two splits, we get "aa" + "dbbc" + "bc" + "a" + "c" = "aadbbcbcac".
 * Since s3 can be obtained by interleaving s1 and s2, we return true.
 * Example 2:
 * <p>
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 * Explanation: Notice how it is impossible to interleave s2 with any other string to obtain s3.
 * Example 3:
 * <p>
 * Input: s1 = "", s2 = "", s3 = ""
 * Output: true
 * <p>
 * Constraints:
 * <p>
 * 0 <= s1.length, s2.length <= 100
 * 0 <= s3.length <= 200
 * s1, s2, and s3 consist of lowercase English letters.
 */
public class InterleavingString {

    public boolean isInterleave3(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length())
            return false;

        boolean dp[][] = new boolean[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0)
                    dp[i][j] = true;

                else if (i == 0)
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);

                else if (j == 0)
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);

                else
                    dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
                            || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }

        return dp[s1.length()][s2.length()];
    }

    // Time complexity: O(m*n)
    // Space complexity: O(m*n)
    public static boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length())
            return false;

        Boolean[][] memo = new Boolean[s1.length() + 1][s2.length() + 1];

        return helper(s1, s2, s3, 0, 0, memo);
    }

    // Top Down DP (Recursion + Memoization)
    // !!! This memo seems not quit useful due to the i, j is always increasing!!!
    public static boolean helper(String s1, String s2, String s3, int i, int j, Boolean[][] memo) {
        if (i + j == s3.length())
            return true;

        if (memo[i][j] != null) {
            System.out.println("I've seen memo index: " + i + "," + j);
            return memo[i][j];
        }

        if (i < s1.length() && s3.charAt(i + j) == s1.charAt(i)) {
            boolean flag = helper(s1, s2, s3, i + 1, j, memo);
            memo[i][j] = flag;
            if (flag)
                return true;
        }

        if (j < s2.length() && s3.charAt(i + j) == s2.charAt(j)) {
            boolean flag = helper(s1, s2, s3, i, j + 1, memo);
            memo[i][j] = flag;
            if (flag)
                return true;
        }

        memo[i][j] = false;
        return false;
    }

    public static void main(String[] args) {
        String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac";
        if (isInterleave(s1, s2, s3)) {
            System.out.println("true");
        }
        ;
    }

    // Bruce force
    // Time complexity: O(2^(m+n))
    // Space complexity: O(m+n)
    public boolean isInterleave2(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length())
            return false;

        return helper2(s1, s2, s3, 0, 0);
    }

    public boolean helper2(String s1, String s2, String s3, int i, int j) {
        if (i + j == s3.length())
            return true;

        if (i < s1.length() && s3.charAt(i + j) == s1.charAt(i)) {
            boolean flag = helper2(s1, s2, s3, i + 1, j);
            if (flag)
                return true;
        }

        if (j < s2.length() && s3.charAt(i + j) == s2.charAt(j)) {
            boolean flag = helper2(s1, s2, s3, i, j + 1);
            if (flag)
                return true;
        }

        return false;
    }
}
