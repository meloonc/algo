package org.melon.dp;

/**
 * 砍怪兽的概率
 * 有一只怪兽，它有 N 滴血，每次攻击都会掉 0~M 滴血，砍 K 次，返回砍死的概率
 */
public class KillMonster {

    public double kill(int N, int M, int K) {
        int process = process(N, M, K);
        return process / Math.pow(M + 1, K);
    }


    public int process(int rest, int M, int K) {
        if (K == 0) {
            return rest <= 0 ? 1 : 0;
        }
        // 如果剩余的血量已经 <= 0，能砍死怪兽的次数 = Math.pow(M + 1, K)
        if (rest <= 0) {
            return (int) Math.pow(M + 1, K);
        }
        int total = 0;
        for (int i = 0; i <= M; i++) {
            total += process(rest - i, M, K - 1);
        }
        return total;
    }

    public double dp(int N, int M, int K) {
        int[][] dp = new int[N + 1][K + 1];
        dp[0][0] = 1;


        for (int rest = 1; rest <= N; rest++) {
            for (int j = 1; j <= K; j++) {
                dp[0][j] = (int) Math.pow(M + 1, j);
                long total = 0;
                for (int m = 0; m <= M; m++) {
                    if (rest - m >= 0) {
                        total += dp[rest - m][j - 1];
                    } else {
                        total += (int) Math.pow(M + 1, j - 1);
                    }
                }
                dp[rest][j] = (int) total;
            }
        }
        return dp[N][K] / Math.pow(M + 1, K);

    }

    public double dp2(int N, int M, int K) {
        int[][] dp = new int[N + 1][K + 1];
        dp[0][0] = 1;
        for (int rest = 1; rest <= N; rest++) {
            for (int j = 1; j <= K; j++) {
                // 斜率优化
                dp[0][j] = (int) Math.pow(M + 1, j);
                if (rest - M - 1 >= 0) {
                    dp[rest][j] = dp[rest - 1][j] + dp[rest][j - 1] - dp[rest - M - 1][j - 1];
                } else {
                    // 如果血量小于 0
                    dp[rest][j] = dp[rest - 1][j] + dp[rest][j - 1] - (int) Math.pow(M + 1, j - 1);
                }
            }
        }
        return dp[N][K] / Math.pow(M + 1, K);

    }

}
