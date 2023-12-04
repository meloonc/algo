package org.melon.dp;

/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。每个值都认为是一张货币,
 * 即便是值相同的货币也认为每一张都是不同的,返回组成aim的方法数
 * 例如 : arr =[1,1,1]，aim =2
 * 第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2共就3种方法，所以返回3
 */
public class CoinChange3 {

    public int change(int[] coins, int amount) {
        return process(coins, amount, 0);
    }


    public int process(int[] coins, int rest, int index) {
        if (rest < 0) {
            return 0;
        }
        // 拿到了最后一张
        if (index == coins.length) {
            return rest == 0 ? 1 : 0;
        }

        // 拿当前这张
        int p1 = process(coins, rest - coins[index], index + 1);
        // 不拿当前这张
        int p2 = process(coins, rest, index + 1);
        return p1 + p2;
    }

    public int dp(int[] coins, int amount) {
        int[][] dp = new int[amount + 1][coins.length + 1];
        dp[0][coins.length] = 1;
        for (int i = 0; i < amount + 1; i++) {
            for (int j = coins.length - 1; j >= 0; j--) {
                dp[i][j] = dp[i][j+1];
                if(i-coins[j] >=0) {
                    dp[i][j] += dp[i-coins[j]][j+1];
                }
            }
        }
        return dp[amount][0];
    }
}
