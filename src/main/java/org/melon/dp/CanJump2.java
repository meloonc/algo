package org.melon.dp;

/**
 * <a href="https://leetcode.cn/problems/jump-game-ii/">45. 跳跃游戏2</a>
 */
public class CanJump2 {

    public static void main(String[] args) {
        int[] nums = {2, 3, 0, 1, 4};

        CanJump2 canJump2 = new CanJump2();
        int jump = canJump2.dp(nums);
        int jump2 = canJump2.jump3(nums);
        System.out.println(jump);
        System.out.println(jump2);
    }

    public int jump(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        // 从 0 位置开始
        return process1(nums, 0);

    }

    public int process1(int[] nums, int index) {
        int step = nums[index];
        if (step == 0) {
            return Integer.MAX_VALUE;
        }
        if (index + step >= nums.length - 1) {
            return 1;
        }
        int min = Integer.MAX_VALUE;
        while (step > 0) {
            int process = process1(nums, index + step);
            if (process != Integer.MAX_VALUE) {
                min = Math.min(process + 1, min);
            }
            step--;
        }
        return min;
    }




    public int dp(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[n - 1] = 0;
        for (int i = n - 2; i >= 0; i--) {
            int min = Integer.MAX_VALUE;
            if (nums[i] + i >= n - 1) {
                min = 1;
            } else {
                for (int j = 1; j <= nums[i]; j++) {
                    if (dp[i + j] == Integer.MAX_VALUE) {
                        continue;
                    }
                    min = Math.min(min, dp[i + j]);
                }
                if (min != Integer.MAX_VALUE) {
                    min++;
                }
            }
            dp[i] = min;
        }
        return dp[0];


    }

    public int jump3(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int prev = 0;
        for (int i = 1; i < n; i++) {
            while (prev + nums[prev] < i) {
                prev++;
            }
            dp[i] = dp[prev] + 1;
        }
        return dp[n - 1];
    }


}
