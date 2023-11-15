package org.melon.dp;

/**
 * 给定一个整型数组arr代表数值不同的纸牌排成一条线
 * 玩家A和玩家B依次拿走每一张牌
 * 规定玩家A先拿玩家B后拿但每个玩家每次只能拿最左或者最右的牌
 * 玩家A和玩家B智商一样
 * 请返回最后获胜者的分数
 */
public class CardLine {

    public static int win1(int[] arr) {
        return Math.max(first1(arr, 0, arr.length - 1), second1(arr, 0, arr.length - 1));
    }

    // 先手拿牌
    public static int first1(int[] arr, int l, int r) {
        // 如果是先手，可以拿到最后这张牌
        if (l == r) {
            return arr[l];
        }
        // 先手拿了 l 位置的牌，后手需要从 l+1 .. r 位置拿牌
        // 如果先手那过了，后手只能选最小值
        int p1 = arr[l] + second1(arr, l + 1, r);
        // 先手拿了 r 位置的牌，后手需要从 l .. r-1 位置拿牌
        int p2 = arr[r] + second1(arr, l, r - 1);
        // 返回先手拿牌的最大值
        return Math.max(p1, p2);

    }

    // 后手拿牌，后手只能拿最小的牌
    public static int second1(int[] arr, int l, int r) {
        // 如果是后手，是拿不到最后这张牌
        if (l == r) {
            return 0;
        }
        // 先手拿了 l 位置的牌，后手需要从 l+1 .. r 位置拿牌
        int p1 = first1(arr, l + 1, r);
        // 先手拿了 r 位置的牌，后手需要从 l .. r-1 位置拿牌
        int p2 = first1(arr, l, r - 1);
        // 返回后手拿牌的最小值
        return Math.min(p1, p2);
    }


    public static int win2(int[] arr) {
        int[][] f = new int[arr.length][arr.length];
        int[][] s = new int[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                f[i][j] = -1;
                s[i][j] = -1;
            }
        }
        return Math.max(first2(arr, 0, arr.length - 1, f), second2(arr, 0, arr.length - 1, s));
    }


    public static int first2(int[] arr, int l, int r, int[][] f) {
        if (l == r) {
            f[l][l] = arr[l];
            return arr[l];
        }
        if (f[l][r] != -1) {
            return f[l][r];
        }

        int p1 = arr[l] + second2(arr, l + 1, r, f);
        int p2 = arr[r] + second2(arr, l, r - 1, f);
        f[l][r] = Math.max(p1, p2);
        return f[l][r];
    }

    public static int second2(int[] arr, int l, int r, int[][] s) {
        if (l == r) {
            s[l][l] = 0;
            return 0;
        }
        if (s[l][r] != -1) {
            return s[l][r];
        }
        int p1 = first2(arr, l + 1, r, s);
        int p2 = first2(arr, l, r - 1, s);
        s[l][r] = Math.min(p1, p2);
        return s[l][r];

    }

    public static int win3(int[] arr) {
        int n = arr.length;
        // 建立两个二维数组， 数组中的值就是拿牌的结果
        // 先手的结果
        int[][] f = new int[n][n];
        // 后手的结果
        int[][] s = new int[n][n];
        for (int i = 0; i < n; i++) {
            // 代表当 l == r 时，结果就是 arr[i] 的值
            f[i][i] = arr[i];
        }

        for (int i = 1; i < n; i++) {
            int r = i;
            int l = 0;
            while (r < n) {
                f[l][r] = Math.max(arr[l] + s[l + 1][r], arr[r] + s[l][r - 1]);
                s[l][r] = Math.min(f[l + 1][r], f[l][r - 1]);
                l++;
                r++;
            }

        }
        return Math.max(f[0][n - 1], s[0][n - 1]);
    }


    public static void main(String[] args) {

        int[] arr = {10, 20, 30};
        System.out.println(win1(arr));
        System.out.println(win2(arr));

        System.out.println(win3(arr));

    }
}
