package org.melon.dp;

/**
 * 象棋问题 马走日
 * 马在 （0，0） 位置在走 n 步后到达 （a,b） 位置的方法，棋盘大小 10 * 9
 */
public class Chess {

    public int chess(int n, int a, int b) {
        return process(0, 0, n, a, b);
    }

    /**
     * 在 （x,y） 位置 剩余 rest 步，需要到 （a,b）
     *
     * @param x
     * @param y
     * @param rest
     * @param a
     * @param b
     * @return
     */
    public int process(int x, int y, int rest, int a, int b) {
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        // 如果步数走完了，位置在指定位置，返回 1
        if (rest == 0) {
            return a == x && y == b ? 1 : 0;
        }
        int p1 = process(x + 1, y + 2, rest - 1, a, b);
        int p2 = process(x + 2, y + 1, rest - 1, a, b);
        int p3 = process(x - 1, y - 2, rest - 1, a, b);
        int p4 = process(x - 2, y - 1, rest - 1, a, b);
        int p5 = process(x + 1, y - 2, rest - 1, a, b);
        int p6 = process(x + 2, y - 1, rest - 1, a, b);
        int p7 = process(x - 1, y + 2, rest - 1, a, b);
        int p8 = process(x - 2, y + 1, rest - 1, a, b);
        return p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8;
    }

    public int dp(int n, int a, int b) {

        int[][][] dp = new int[10][9][n + 1];
        dp[a][b][0] = 1;

        for (int rest = 1; rest < n; rest++) {

            for (int y = 0; y < 9; y++) {
                for (int x = 0; x < 10; x++) {
                    int p1 = x + 1 < 10 && y + 2 < 9 ? dp[x + 1][y + 2][rest - 1] : 0;
                    int p2 = x + 2 < 10 && y + 1 < 9 ? dp[x + 2][y + 1][rest - 1] : 0;
                    int p3 = x - 1 >= 0 && y - 2 >= 0 ? dp[x - 1][y - 2][rest - 1] : 0;
                    int p4 = x - 2 >= 0 && y - 1 >= 0 ? dp[x - 2][y - 1][rest - 1] : 0;
                    int p5 = x + 1 < 10 && y - 2 >= 0 ? dp[x + 1][y - 2][rest - 1] : 0;
                    int p6 = x + 2 < 10 && y - 1 >= 0 ? dp[x + 2][y - 1][rest - 1] : 0;
                    int p7 = x - 1 >= 0 && y + 2 < 9 ? dp[x - 1][y + 2][rest - 1] : 0;
                    int p8 = x - 2 >= 0 && y + 1 < 9 ? dp[x - 2][y + 1][rest - 1] : 0;
                    dp[x][y][rest] = p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8;
                }
            }

        }


        return dp[0][0][n];


    }
}
