package org.melon.dp;

import java.util.Arrays;

/**
 * <a href="https://leetcode-cn.com/problems/house-robber/">198. 打家劫舍</a>
 */
public class CoinChange {

    public static void main(String[] args) {
        int[] coins = {1};
        int amount = 0;
        CoinChange coinChange = new CoinChange();
        int min = coinChange.coinChange(coins, amount);

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
        return dp[amount] ==  Integer.MAX_VALUE ? -1 : dp[amount];
    }
}
