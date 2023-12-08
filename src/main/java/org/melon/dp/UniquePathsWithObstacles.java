package org.melon.dp;

/**
 * <a href="https://leetcode.cn/problems/unique-paths-ii/">63. 不同路径 II</a>
 */
public class UniquePathsWithObstacles {

    public static void main(String[] args) {
        UniquePathsWithObstacles uniquePathsWithObstacles = new UniquePathsWithObstacles();
        int[][] obstacleGrid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        uniquePathsWithObstacles.uniquePathsWithObstacles(obstacleGrid);

    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) {
            return 0;
        }
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[m-1][n-1] == 1) {
            return 0;
        }
        return process(obstacleGrid, 0, 0);
    }

    /**
     * 从(i,j) 位置到右下角有几条路径
     */
    public int process(int[][] obstacleGrid, int i, int j) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (i == m - 1 && j == n - 1) {
            return 1;
        }
        int p1 = 0;
        // 向右走
        if (j + 1 < n && obstacleGrid[i][j + 1] != 1) {
            p1 = process(obstacleGrid, i, j + 1);
        }

        int p2 = 0;
        // 向下走
        if (i + 1 < m && obstacleGrid[i + 1][j] != 1) {
            p2 = process(obstacleGrid, i + 1, j);
        }
        return p1 + p2;
    }

    public int dp(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1) {
            return 0;
        }

        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = 1;
        for (int i = m - 2; i >= 0; i--) {
            if (obstacleGrid[i][n - 1] == 1) {
                dp[i][n - 1] = 0;
            } else {
                dp[i][n - 1] = dp[i + 1][n - 1];
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            if (obstacleGrid[m - 1][i] == 1) {
                dp[m - 1][i] = 0;
            } else {
                dp[m - 1][i] = dp[m - 1][i + 1];
            }

        }


        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                if(obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                }else {
                    dp[i][j] = dp[i+1][j] + dp[i][j+1];
                }
            }
        }

        return dp[0][0];


    }

}
