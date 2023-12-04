package org.melon.dp;

/**
 * <a href="https://leetcode-cn.com/problems/coin-change-2/">518. 零钱兑换 II</a>
 */
public class CoinChange2 {

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        CoinChange2 coinChange2 = new CoinChange2();
        int change = coinChange2.change(5, coins);
        System.out.println(change);
    }

    public int change(int amount, int[] coins) {
        return process(coins, 0, amount);
    }


    /**
     * index 指的是第几个货币， rest 是剩余的金额
     *
     * @param coins
     * @param index
     * @param rest
     * @return
     */
    public int process(int[] coins, int index, int rest) {
        // 这个判断条件可以忽略，在 i* coins[index] <= rest 中有隐式包含
//        if (rest < 0) {
//            return 0;
//        }
        // 所有的货币都判断过

        if (index == coins.length) {
            return rest == 0 ? 1 : 0;
        }

        int count = 0;
        // 这个循环在判断 拿了 i 张 index 对应的货币有几种方法
        for (int i = 0; i * coins[index] <= rest; i++) {
            // 递归下一张货币
            count += process(coins, index + 1, rest - i * coins[index]);
        }
        return count;

    }


    public int dp(int amount, int[] coins) {
        int[][] dp = new int[amount + 1][coins.length + 1];
        dp[0][coins.length] = 1;

        for (int rest = 0; rest <= amount; rest++) {
            for (int index = coins.length - 1; index >= 0; index--) {
                int count = 0;
                // 这个循环在判断 拿了 i 张 index 对应的货币有几种方法
                // 拿 0 张 index 货币
                // 拿 1 张 index 货币
                // ... 一直到不能拿 index 货币
//                for (int i = 0; i * coins[index] <= rest; i++) {
//                    // 递归下一张货币
//                    count += dp[rest - i * coins[index]][index + 1];
//                }
                // 通过严格表结构观察发现 dp[rest-coins[index]][index] 就是之前的累加和 可以去掉枚举 可以优化为以下
                count = dp[rest][index+1];
                if(rest-coins[index] >=0) {
                    count  += dp[rest-coins[index]][index];
                }

                dp[rest][index] = count;
            }
        }

        return dp[amount][0];


    }

}
