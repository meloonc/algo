package org.melon.dp;

/**
 * <a href="https://leetcode-cn.com/problems/minimum-path-sum/">64. 最小路径和</a>
 */
public class MinPathSum {

    public static void main(String[] args) {
        MinPathSum minPathSum = new MinPathSum();
        int i = minPathSum.dp(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}});
        System.out.println(i);
    }

    /**
     * (0,0)->(m,n)
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        return process(grid, 0, 0);
    }


    public int process(int[][] grid, int m, int n) {
        if (m >= grid.length || n >= grid[0].length) {
            return Integer.MAX_VALUE;
        }
        if (m == grid.length - 1 && n == grid[0].length - 1) {
            return grid[m][n];
        }
        // 向下走
        int p1 = process(grid, m + 1, n);
        // 向右走
        int p2 = process(grid, m, n + 1);
        return grid[m][n] + Math.min(p1, p2);
    }


    public int dp(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // 空间可以压缩，因为每次只需要用到下一行的数据

        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = grid[m - 1][n - 1];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j == n - 1) {
                    continue;
                }
                if (i == m - 1) {
                    dp[i][j] = grid[i][j] + dp[i][j + 1];
                } else if (j == n - 1) {
                    dp[i][j] = grid[i][j] + dp[i + 1][j];
                } else {
                    dp[i][j] = grid[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        return dp[0][0];
    }
}
