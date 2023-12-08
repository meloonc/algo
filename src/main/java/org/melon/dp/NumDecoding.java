package org.melon.dp;

/**
 * <a href="https://leetcode.cn/problems/decode-ways/">91. 解码方法</a>
 */
public class NumDecoding {

    public static void main(String[] args) {
        NumDecoding numDecoding = new NumDecoding();
        System.out.println(numDecoding.numDecodings("06"));
        System.out.println(numDecoding.dp("06"));
    }

    public int numDecodings(String s) {
        char[] chars = s.toCharArray();
        return process(chars, 0);

    }

    public int process(char[] chars, int index) {
        if (index == chars.length) {
            return 1;
        }
        // 0 开头的数字无法转换
        if (chars[index] == '0') {
            return 0;
        }
        // 当前字符单独转数字的可能性
        int ways = process(chars, index + 1);
        // 当前字符与下一个字符一起转的可能性
        // 如果可以一起转，即 index+1 未越界，并且当前字符和下一个字符组成的数字小于等于 26
        if (index + 1 < chars.length && ((chars[index] - '0') * 10 + chars[index + 1] - '0' <= 26)) {
            ways += process(chars, index + 2);
        }
        return ways;
    }

    public int dp(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length + 1;
        int[] dp = new int[n];
        dp[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            if(chars[i] != '0'){
                int ways = dp[i + 1];
                if (i + 1 < chars.length && ((chars[i] - '0') * 10 + chars[i + 1] - '0' <= 26)) {
                    ways += dp[i + 2];
                }
                dp[i] = ways;
            }
        }
        return dp[0];
    }
}
