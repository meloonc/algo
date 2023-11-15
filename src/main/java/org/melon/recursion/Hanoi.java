package org.melon.recursion;

/**
 * 汉诺塔问题
 */
public class Hanoi {

    public static void main(String[] args) {
        Hanoi hanoi = new Hanoi();
        hanoi.left2right(3);

        System.out.println("==============================");

        hanoi.sixInOne(3, "left", "right", "mid");
    }

    public void left2right(int n) {
        if (n == 1) {
            System.out.println(n + " from left to right");
            return;
        }
        // 先将最上面的 n-1 个从左边移动到中间
        left2mid(n - 1);
        // 将最下面的一个从左边移动到右边
        System.out.println(n + " from left to right");
        // 将中间的 n-1 个从中间移动到右边
        mid2right(n - 1);

    }

    public void left2mid(int n) {
        if (n == 1) {
            System.out.println(n + " from left to mid");
            return;
        }
        // 先将最上面的 n-1 个从左边移动到右边
        left2right(n - 1);
        System.out.println(n + " from left to mid");
        // 将右边的 n-1 个从右边移动到中间
        right2mid(n - 1);
    }

    public void right2mid(int n) {
        if (n == 1) {
            System.out.println(n + " from right to mid");
            return;
        }
        // 先将最上面的 n-1 个从右边移动到左边
        right2left(n - 1);
        System.out.println(n + " from right to mid");
        // 将左边的 n-1 个从左边移动到中间
        left2mid(n - 1);
    }

    public void right2left(int n) {
        if (n == 1) {
            System.out.println(n + " from right to left");
            return;
        }
        // 先将最上面的 n-1 个从右边移动到中间
        right2mid(n - 1);
        System.out.println(n + " from right to left");
        // 将中间的 n-1 个从中间移动到左边
        mid2left(n - 1);
    }

    public void mid2right(int n) {
        if (n == 1) {
            System.out.println(n + " from mid to right");
            return;
        }
        // 先将最上面的 n-1 个从中间移动到左边
        mid2left(n - 1);
        System.out.println(n + " from mid to right");
        // 将左边的 n-1 个从左边移动到右边
        left2right(n - 1);
    }

    public void mid2left(int n) {
        if (n == 1) {
            System.out.println(n + " from mid to left");
            return;
        }
        // 先将最上面的 n-1 个从中间移动到右边
        mid2right(n - 1);
        System.out.println(n + " from mid to left");
        // 将右边的 n-1 个从右边移动到左边
        right2left(n - 1);
    }

    // 六合一
    public void sixInOne(int n, String from, String to, String other) {
        if (n == 1) {
            System.out.println(n + " from " + from + " to " + to);
            return;
        }
        sixInOne(n - 1, from, other, to);
        System.out.println(n + " from " + from + " to " + to);
        sixInOne(n - 1, other, to, from);
    }
}
