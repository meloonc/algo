package org.melon.dp;

/**
 * <a href="https://leetcode.cn/problems/house-robber/">198. 打家劫舍</a>
 */
public class Rob {

    public static void main(String[] args) {
        int[] nums = {2,1,1,2};
        Rob rob = new Rob();

        System.out.println(rob.rob(nums));
        System.out.println(rob.dp(nums));
    }

    public int rob(int[] nums) {
        return process(nums, 0);
    }

    public int process(int[] nums, int index) {
        if (index > nums.length - 1) {
            return 0;
        }
        int p1 = nums[index] + process(nums, index + 2);
        int p2 = process(nums, index + 1);
        return Math.max(p1, p2);
    }

    public int dp(int[] nums){

        int n = nums.length;
        if(n == 1) {
            return nums[0];
        }
        if(n==2) {
            return Math.max(nums[0], nums[1]);
        }

        int[] dp = new int[n];
        dp[n-1] = nums[n-1];
        dp[n-2] = Math.max(nums[n-1], nums[n-2]);

        for (int i = n-3; i >=0 ; i--) {
            int p1 = nums[i] + dp[i+2];
            int p2 = dp[i + 1];
            dp[i] =  Math.max(p1, p2);
        }
        return dp[0];

    }
}
