package org.melon.dp;

/**
 * 背包问题
 * w 重量数组
 * v 价值数组
 * 在背包容量以内获取最大价值
 */
public class Knapsack {
    public static void main(String[] args) {
        int[] w = {1, 4, 5, 2};
        int[] v = {2, 5, 1, 3};
        int bag = 8;
        Knapsack knapsack = new Knapsack();
        int maxValue = knapsack.maxValue(w, v, bag);
        System.out.println(maxValue);

        int maxValue2 = knapsack.maxValue2(w, v, bag);
        System.out.println(maxValue2);
    }

    /**
     * @param w   重量数组
     * @param v   价值数组
     * @param bag 背包容量
     * @return 最大价值
     */
    public int maxValue(int[] w, int[] v, int bag) {
        return process(w, v, bag, 0);
    }

    public int process(int[] w, int[] v, int rest, int index) {
        // 背包容量不足
        if (rest < 0) {
            return 0;
        }
        // 越界位置
        if (index == w.length) {
            return 0;
        }
        // 如果当前位置的重量大于剩余容量，那么不选择当前位置
        if (w[index] > rest) {
            return process(w, v, rest, index + 1);
        }
        // 选择当前位置

        int p1 = process(w, v, rest - w[index], index + 1) + v[index];
        // 不选择当前位置
        int p2 = process(w, v, rest, index + 1);
        return Math.max(p1, p2);
    }

    // dp
    public int maxValue2(int[] w, int[] v, int bag) {
        // 创建动态规划数组
        // 行代表货物下标，由于在递归中有判断 index == w.length，所以行数为 w.length + 1，即 0 - w.length 位置都需要考虑
        // 列代表背包容量。由于在递归中有判断 rest < 0，所以列数为 bag + 1， 即 0 - bag 位置都需要考虑
        int[][] dp = new int[w.length + 1][bag + 1];
        // base case 是最后一行数据都等 0 ，当前数据依赖下一行数据(index+1)，所以从下往上遍历
        for (int index = w.length - 1; index >= 0; index--) {
            // 正常遍历
            for (int rest = 0; rest <= bag; rest++) {
                // 递归中的尝试逻辑就是动态转移方程
                // 如果当前位置的重量大于剩余容量，那么不选择当前位置
                if (rest - w[index] < 0) {
                    dp[index][rest] = dp[index + 1][rest];
                } else {
                    dp[index][rest] = Math.max(dp[index + 1][rest], v[index] + dp[index + 1][rest - w[index]]);
                }
            }
        }
        // 递归主函数调用的是 （0，bag）,即动态规划所对应的数组中的位置
        return dp[0][bag];
    }

}
