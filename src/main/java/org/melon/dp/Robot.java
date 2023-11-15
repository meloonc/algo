package org.melon.dp;

import java.util.Arrays;

/**
 * 假设有排成一行的N个位置记为1到N
 * N一定大于等于2开始时机器人在其中的M位置上M一定是1到N中的一个
 * 如果机器人来到1位置那么下一步只能往右来到2位置
 * 如果机器人来到N位置那么下一步只能往左来到N-1的位置
 * 如果机器人初始位置为 start，目标为 aim 位置，可以走 k 步， 有几种走路的方式
 */
public class Robot {

    public static void main(String[] args) {
        Robot robot = new Robot();
        System.out.println(robot.walk(1, 3, 2, 4));
        System.out.println(robot.walk2(1, 3, 2, 4));
        System.out.println(robot.walk3(1, 3, 2, 4));
    }


    /**
     * @param start 初始位置
     * @param k     步数
     * @param aim   目标位置
     * @param n     位置数
     * @return
     */
    public int walk(int start, int k, int aim, int n) {
        return process(start, k, aim, n);
    }

    /**
     * 重复进行了计算，需要对结果进行缓存
     *
     * @param cur
     * @param rest
     * @param aim
     * @param n
     * @return
     */
    public int process(int cur, int rest, int aim, int n) {
        if (rest == 0) {
            return cur == aim ? 1 : 0;
        }
        if (cur == 1) {
            return process(cur + 1, rest - 1, aim, n);
        }
        if (cur == n) {
            return process(cur - 1, rest - 1, aim, n);
        }
        return process(cur - 1, rest - 1, aim, n) + process(cur + 1, rest - 1, aim, n);

    }


    /**
     * @param start 初始位置
     * @param k     步数
     * @param aim   目标位置
     * @param n     位置数
     * @return
     */
    public int walk2(int start, int k, int aim, int n) {
        // 用来缓存结果
        int[][] dp = new int[n + 1][k + 1];
        // 初始化二维数组，如果值 = -1 ，表示没有走过
        for (int[] ints : dp) {
            Arrays.fill(ints, -1);
        }
        return process2(start, k, aim, n, dp);

    }

    /**
     * dp 数组用来缓存结果，dp[i][j] 表示 i 位置， 剩余 j 步的结果
     *
     * @param cur
     * @param rest
     * @param aim
     * @param n
     * @param dp
     * @return
     */
    public int process2(int cur, int rest, int aim, int n, int[][] dp) {
        // 减少重复计算
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }
        if (rest == 0) {
            return aim == cur ? 1 : 0;
        }
        int ans;
        if (cur == 1) {
            ans = process2(cur + 1, rest - 1, aim, n, dp);
        } else if (cur == n) {
            ans = process2(cur - 1, rest - 1, aim, n, dp);
        } else {
            ans = process2(cur - 1, rest - 1, aim, n, dp) + process2(cur + 1, rest - 1, aim, n, dp);
        }
        dp[cur][rest] = ans;
        return ans;
    }

    /**
     * 动态规划，根据 walk2 推到出来，不需要递归，只需要填充二维数组即可
     * 1. base case， 当已经到达目标位置，并且步数 = 0，值为 1， 其他位置都是 0， 即[aim][0] = 1，剩余的[...][0] 位置 = 0
     * 2. dp[1][k] = dp[2][k - 1]
     * 3. dp[n][k] = dp[n - 1][k - 1]
     * 4. 状态转移方程， dp[i][j] = dp[i - 1][j - 1] + dp[i + 1][j - 1]
     * @param start 初始位置
     * @param k     步数
     * @param aim   目标位置
     * @param n     位置数
     * @return
     */
    public int walk3(int start, int k, int aim, int n) {
        // 返回结果就在 dp 数组中 ，dp[start][k]
        int[][] dp = new int[n + 1][k + 1];
        // base case， 当已经到达目标位置，并且步数 = 0，值为 1， 其他位置都是 0
        dp[aim][0] = 1;
        for (int rest = 1; rest <= k; rest++) {
            dp[1][rest] = dp[2][rest - 1];
            for (int cur = 2; cur < n; cur++) {
                dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
            }
            dp[n][rest] = dp[n - 1][rest - 1];
        }
        return dp[start][k];

    }


}
