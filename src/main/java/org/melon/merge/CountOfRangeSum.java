package org.melon.merge;

import static org.melon.sort.SelectionSort.arr;

/**
 * 给你一个整数数组 nums 以及两个整数 lower 和 upper 。求数组中，值位于范围 [lower, upper] （包含 lower 和 upper）之内的 区间和的个数 。
 * <p>
 * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
 * <p>
 * <p>
 * nums 转化为前缀和数组 sum，即 sum 中的元素是 nums 中 0 位置到当前位置的和
 * sum[j] - sum[i] = [i...j] 的和, [i...j] 的和需要位于 [lower,upper] 之间
 */
public class CountOfRangeSum {

    public static void main(String[] args) {
        int[] arr = arr();
        int lower = 10;
        int upper = 20;
        // 前缀和数组
        int[] sum = new int[arr.length];
        int i = 0;
        sum[0] = arr[0];
        for (i = 1; i < arr.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        int process = process(sum, 0, sum.length - 1, lower, upper);
        System.out.println("个数：" + process);

    }

    public static int process(int[] arr, int l, int r, int lower, int upper) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 说明 0...l 位置的前缀和为 arr[l]
        if (l == r) {
            // 如果在区间内，返回 1
            if (arr[l] >= lower && arr[l] <= upper) {
                return 1;
            } else {
                return 0;
            }
        }
        int m = l + ((r - l) >> 1);
        return process(arr, l, m, lower, upper) + process(arr, m + 1, r, lower, upper) + merge(arr, l, m, r, lower, upper);


    }

    public static int merge(int[] arr, int l, int m, int r, int lower, int upper) {
        int ans = 0;
        int windowR = l;
        int windowL = l;
        // 从 l 开始遍历
        for (int i = l; i < m + 1; i++) {
            // 计算区间和最小值、最大值
            int max = arr[i] - lower;
            int min = arr[i] - upper;
            while (windowL <= m && arr[windowL] <= max) {
                windowL++;
            }
            while (windowR <= m && arr[windowR] > min) {
                windowR++;
            }
            ans += windowR - windowL;
        }

        // 归并排序
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        int[] helper = new int[r - l + 1];
        while (p1 <= m && p2 <= r) {
            if (arr[p1] <= arr[p2]) {
                helper[i++] = arr[p1++];
            } else {
                helper[i++] = arr[p2++];
            }
        }
        while (p1 <= m) {
            helper[i++] = arr[p1++];
        }
        while (p2 <= r) {
            helper[i++] = arr[p2++];
        }
        for (i = 0; i < helper.length; i++) {
            arr[l + i] = helper[i];
        }
        return ans;
    }
}
