package org.melon.dp;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/coin-change/">322. 零钱兑换</a>
 */
public class CoinChange {

    public static void main(String[] args) {
        int[] coins = {1,2,5};
        int amount = 11;
        CoinChange coinChange = new CoinChange();
        int min = coinChange.coinChange(coins, amount);
        int dp2 = coinChange.dp2(coins, amount);

        System.out.println(min);
    }

    public int coinChange(int[] coins, int amount) {
        int min = process(coins, amount, 0);
        if (min == Integer.MAX_VALUE) {
            return -1;
        }
        return min;
    }

    public int process(int[] coins, int rest, int count) {
        if (rest < 0) {
            return Integer.MAX_VALUE;
        }
        if (rest == 0) {
            return count;
        }
        int minCount = Integer.MAX_VALUE;
        for (int coin : coins) {
            int temp = count;
            temp++;
            minCount = Math.min(process(coins, rest - coin, temp), minCount);
        }
        return minCount;

    }

    public int dp(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        // 当金额 = 0 时，只需要 0 张。
        dp[0] = 0;
        // 从余额 = 1 时开始计算
        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                // 如果当前余额可以拿当前货币
                if (i - coin >= 0) {
                    // 扣除到当前货币后的余额也能拿就是实际上的最小值
                    if (dp[i - coin] != Integer.MAX_VALUE) {
                        min = Math.min(dp[i - coin] + 1, min);
                    }
                }
            }
            dp[i] = min;

        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }


    public int min(int[] coins, int rest, int index) {
        if (index == coins.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }

        int min = Integer.MAX_VALUE;
        for (int zhang = 0; zhang * coins[index] <= rest; zhang++) {
            int next = min(coins, rest - zhang * coins[index], index + 1);
            if (next != Integer.MAX_VALUE) {
                min = Math.min(min, next + zhang);
            }
        }
        return min;
    }


    public int dp2(int[] coins, int amount) {
        int n = coins.length;

        int[][] dp = new int[amount + 1][n+1];

        for (int i = 0; i <=amount; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][n] = 0;

        for (int rest = 0; rest <= amount; rest++) {
            for (int index = n - 1; index >= 0; index--) {
                int min = Integer.MAX_VALUE;
                for (int zhang = 0; zhang * coins[index] <= rest; zhang++) {
                    int next = dp[rest - zhang * coins[index]][index + 1];
                    if (next != Integer.MAX_VALUE) {
                        min = Math.min(min, next + zhang);
                    }
                }
                dp[rest][index] = min;
            }
        }
        return dp[amount][0] == Integer.MAX_VALUE ? -1 : dp[amount][0];


    }
}
