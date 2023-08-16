package org.melon.recursion;

/**
 * 使用递归查询最大数
 */
public class MaxNumber {

    public static int getMax(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int l, int r) {
        // base case 推出递归的条件
        if (l == r) {
            return arr[l];
        }
        int mid = l + ((r - l) >> 1);
        int maxInLeft = process(arr, l, mid);
        int maxInRight = process(arr, mid + 1, r);
        return Math.max(maxInLeft, maxInRight);
    }
}
