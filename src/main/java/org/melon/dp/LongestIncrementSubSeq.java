package org.melon.dp;

import java.util.Arrays;

/**
 * 最长递增子序列
 * <a href="https://leetcode-cn.com/problems/longest-increasing-subsequence/">300. 最长递增子序列</a>
 */
public class LongestIncrementSubSeq {

    public static void main(String[] args) {
        int[] arr = {0};

        LongestIncrementSubSeq seq = new LongestIncrementSubSeq();
        seq.lengthOfLIS(arr);
    }

    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0) {
            return  0;
        }
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int res =1;
        for (int i = 1; i < nums.length; i++) {
            int max = 1;
            for (int j = i-1; j>=0; j--) {
                if (nums[i] > nums[j]) {
                    max = Math.max(max, dp[j] + 1);
                }
            }
            dp[i] = max;
            res = Math.max(res, max);
        }
        return res;
    }




}
