package org.melon.dp;

/**
 * 求将正整数n无序拆分成最大数为k（称为n的k拆分）的拆分方案个数，要求所有的拆分方案不重复。（后面的数不能小于前面的数）
 * <p>
 * 所谓的方案不重复，指的是，对于：1 + 1 + 2和2 + 1 + 1是同一个方法，只是顺序不同。
 */
public class SplitNumber {

    public int split(int n) {
        return process(1, n);
    }


    public int process(int pre, int rest) {
        if (rest == 0) {
            return 1;
        }
        if (pre > rest) {
            return 0;
        }

        int res = 0;
        for (int i = pre; i <= rest; i++) {
            res += process(i, rest - i);
        }
        return res;
    }


    public int dp(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
        }

        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                int res = 0;
                for (int i = pre; i <= rest; i++) {
                    res += dp[i][rest - i];
                }
                dp[pre][rest] = res;
            }
        }
        return dp[1][n];

    }

    public int dp2(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
        }

        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                dp[pre][rest] = dp[pre + 1][rest] + dp[pre][rest - pre];
            }
        }
        return dp[1][n];

    }

    public static void main(String[] args) {
        SplitNumber splitNumber = new SplitNumber();
        System.out.println(splitNumber.dp2(5));
        System.out.println(splitNumber.dp(5));
        System.out.println(splitNumber.split(5));
    }

}
