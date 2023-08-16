package org.melon.merge;

import java.util.Arrays;

import static org.melon.sort.SelectionSort.arr;

/**
 * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
 * <p>
 * 你需要返回给定数组中的重要翻转对的数量。
 */
public class ReversePairs {

    public static void main(String[] args) {
        int[] arr = arr();
//        int[] arr = {2147483647,2147483647,2147483647,2147483647,2147483647,2147483647};
        System.out.println("翻转前数组：" + Arrays.toString(arr));
        int i = reversePairs(arr);
        System.out.println("翻转对个数：" + i);
        System.out.println("翻转后数组：" + Arrays.toString(arr));

//        merge(arr, 0, arr.length - 1);
    }

    public static int reversePairs(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        return merge(nums, 0, nums.length - 1);
    }

    public static int merge(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int m = l + ((r - l) >> 1);
        return merge(arr, l, m) + merge(arr, m + 1, r) + process(arr, l, m, r);
    }

    public static int process(int[] arr, int l, int m, int r) {
        int ans = 0;
        int windowR = m + 1;
        for (int j = l; j <= m; j++) {
            // windowR 比较条件放前面，否则会发生越界
            // l...m 位置上，如果有数字大于 m+1 ... r 位置的 2 倍， windowR 就往右边靠，直到条件不符合
            while (windowR <= r && biggerTwice(arr[j], arr[windowR]) ) {
                windowR++;
            }
            ans += (windowR - m - 1);
        }
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

    // 处理越界的情况
    public static boolean biggerTwice(int a, int b) {
        return a / 2 > b || (a / 2 == b && a % 2 == 1);
    }

}
