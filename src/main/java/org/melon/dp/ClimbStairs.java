package org.melon.dp;

public class ClimbStairs {

    public int climbStairs(int n) {
        return process2(n,0);
    }

    public int process(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        return process(n - 1) + process(n - 2);
    }


    public int process2(int n, int cur) {
        if (cur > n) {
            return 0;
        }
        if (n == cur) {
            return 1;
        }

        return process2(n, cur + 1) + process2(n, cur + 2);
    }

    public int dp(int n) {
        int[] dp = new int[n + 1];
        dp[n] = 1;
        dp[n-1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            dp[i] = dp[i+1] + dp[i+2];
        }
        return dp[0];
    }

    public static void main(String[] args) {
        ClimbStairs climbStairs = new ClimbStairs();
        System.out.println(climbStairs.climbStairs(3));
    }
}
