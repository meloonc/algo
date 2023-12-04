package org.melon.dp;

/**
 * 给定5个参数，N，M, row, col, k，表示在N * M的区域上，醉汉Bob初始在(row,col)位置。
 * Bob一共要迈出k步，且每步都会等概率向上下左右四个方向走一个单位。
 * 任何时候Bob只要离开N * M的区域，就直接死亡。返回k步之后，Bob还在N * M的区域的概率。
 */
public class BodDie {

    public double die(int N, int M, int row, int col, int k) {
        return (double) process(N, M, row, col, k) / Math.pow(4, k);
    }


    /**
     * 越界返回 0，没越界返回 1
     *
     * @param N
     * @param M
     * @param row
     * @param col
     * @return
     */
    public int process(int N, int M, int row, int col, int k) {
        if (row < 0 || row >= N || col < 0 || col >= M) {
            return 0;
        }
        if (k == 0) {
            return 1;
        }
        k--;
        return process(N, M, row - 1, col, k) +
                process(N, M, row + 1, col, k) +
                process(N, M, row, col - 1, k) +
                process(N, M, row, col + 1, k);
    }

    // 三维动态规划
    public double dp(int N, int M, int row, int col, int k) {
        int[][][] dp = new int[N + 1][M + 1][k + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                dp[i][j][0] = 1;
            }
        }
        for (int step = 1; step <= k; step++) {
            for (int i = 0; i <= N; i++) {
                for (int j = 0; j <= M; j++) {
                    int left = 0;
                    int right = 0;
                    int up = 0;
                    int down = 0;
                    if (i != 0) {
                        down = dp[i - 1][j][step - 1];
                    }
                    if (i != N) {
                        up = dp[i + 1][j][step - 1];
                    }
                    if (j != 0) {
                        left = dp[i][j - 1][step - 1];

                    }
                    if (j != M) {
                        right = dp[i][j + 1][step - 1];
                    }
                    dp[i][j][step] = left + right + up + down;
                }
            }
        }
        return dp[row][col][k] / Math.pow(4, k);
    }


}
