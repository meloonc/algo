package org.melon.dp;

/**
 * 最长回文子序列
 * <a href="https://leetcode.cn/problems/longest-palindromic-subsequence/">516. 最长回文子序列</a>
 */
public class LongestPalindromeSubSeq {

    // 字符串和翻转字符串的最长公共子序列就是最长回文子序列
    public int longestPalindromeSubSeq(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        return process(chars, 0, n - 1);
    }

    public int process(char[] chars, int l, int r) {
        if (l == r) {
            return 1;
        }
        if (l + 1 == r) {
            return chars[l] == chars[r] ? 2 : 1;
        }
        // 不需要 l r
        int p1 = process(chars, l + 1, r - 1);
        // 需要 l 不需要 r
        int p2 = process(chars, l, r - 1);
        // 不需要 l 需要 r
        int p3 = process(chars, l + 1, r);
        // 需要 l r
        int p4 = chars[l] == chars[r] ? 2 + process(chars, l + 1, r - 1) : 0;
        return Math.max(p1, Math.max(p2, Math.max(p3, p4)));
    }


    public int dp(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[][] dp = new int[n][n];
        dp[n - 1][n - 1] = 1;
        for (int i = 0; i < n - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = chars[i] == chars[i + 1] ? 2 : 1;
        }
        for (int i = n - 3; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                // 需要 l 不需要 r
                int p2 = dp[i][j - 1];
                // 不需要 l 需要 r
                int p3 = dp[i + 1][j];
                // 需要 l r
                int p4 = chars[i] == chars[j] ? 2 + dp[i + 1][j - 1] : 0;
                dp[i][j] = Math.max(p2, Math.max(p3, p4));
            }
        }
        return dp[0][n - 1];

    }


}
