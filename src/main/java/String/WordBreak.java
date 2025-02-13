package String;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 139. Word Break
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 * <p>
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * Example 2:
 * <p>
 * Input: s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * Example 3:
 * <p>
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s and wordDict[i] consist of only lowercase English letters.
 * All the strings of wordDict are unique.
 */
public class WordBreak {
    // memo and dfs
    Boolean[] mem;

    public boolean wordBreak(String s, List<String> wordDict) {
        mem = new Boolean[s.length()];
        return dfs(0, s, new HashSet<String>(wordDict));
    }

    // without the memo, the Brute Force Method time complexity Should Be O(2^n)
    // with memo, n^3 which equal to the DP (n^3)
    private boolean dfs(int p, String s, Set<String> dict) {
        int n = s.length();
        if (p == n) {
            return true;
        }
        if (mem[p] != null) {
            return mem[p];
        }
        for (int i = p + 1; i <= n; i++) {
            if (dict.contains(s.substring(p, i)) && dfs(i, s, dict)) {
                mem[p] = true;
                return true;
            }
        }
        mem[p] = false;
        return false;
    }

    /**
     * For dp problems, many times we go into iterative dp directly without even thinking about dfs.
     * This is a great example showing that dfs is better than dp.
     * DFS returns as soon as it finds one way to break the word while dp computes if each substring starting/ending at i is breakable.
     */
    public boolean wordBreak2(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        Set<String> dict = new HashSet(wordDict);
        for (int i = 1; i < s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && dict.contains(s.substring(j, i - j))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
