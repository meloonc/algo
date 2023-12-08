package org.melon.dp;

/**
 * 最长公共子序列
 * <a href="https://leetcode.cn/problems/longest-common-subsequence/">1143. 最长公共子序列</a>
 */
public class LongestCommonSubSeq {

    public int longestCommonSubsequence(String text1, String text2) {
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
        return process(chars1, chars2, chars1.length - 1, chars2.length - 1);

    }

    private int process(char[] chars1, char[] chars2, int i, int j) {
        // 如果0位置的字符相等，说明存在子序列，长度=1
        if (i == 0 && j == 0) {
            return chars1[i] == chars2[j] ? 1 : 0;
        } else if (i == 0) {
            return chars1[i] == chars2[j] ? 1 : process(chars1, chars2, i, j - 1);
        } else if (j == 0) {
            return chars1[i] == chars2[j] ? 1 : process(chars1, chars2, i - 1, j);
        } else {
            // 以当前位置结束，如果当前位置的字符相等，序列长度 +1，否则长度=0
            int p1 = chars1[i] == chars2[j] ? 1 + process(chars1, chars2, i - 1, j - 1) : 0;
            // 一定不以i位置结束
            int p2 = process(chars1, chars2, i - 1, j);
            // 一定不以 j 位置结束
            int p3 = process(chars1, chars2, i, j - 1);
            return Math.max(p1, Math.max(p2, p3));
        }
    }

    public int dp(String text1, String text2) {
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();

        int[][] dp = new int[chars1.length][chars2.length];
        // 初始化第一行
        dp[0][0] = chars1[0] == chars2[0] ? 1 : 0;
        for (int j = 1; j < dp[0].length; j++) {
            dp[0][j] = chars1[0] == chars2[j] ? 1 : dp[0][j - 1];
        }
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = chars1[i] == chars2[0] ? 1 : dp[i - 1][0];
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                // 以当前位置结束，如果当前位置的字符相等，序列长度 +1，否则长度=0
                int p1 = chars1[i] == chars2[j] ? 1 + dp[i - 1][j - 1] : 0;
                // 一定不以i位置结束
                int p2 = dp[i - 1][j];
                // 一定不以 j 位置结束
                int p3 = dp[i][j - 1];
                dp[i][j]= Math.max(p1, Math.max(p2, p3));
            }
        }
        return dp[chars1.length-1][chars2.length-1];

    }

}
