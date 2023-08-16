package org.melon.merge;

import java.util.Arrays;

import static org.melon.sort.InsertSort.arr;

/**
 * 小和问题
 * 给定一个数组，将每个数左边小于的自己数累加起来
 */
public class SmallSum {


    public static void main(String[] args) {
//        int[] arr = arr();
        int[] arr = {4, 42, 30, 30, 54, 36, 10, 62, 17, 85};
        System.out.println("求和之前:" + Arrays.toString(arr));
        int ans = add(arr, 0, arr.length - 1);
        System.out.println("求和之后:" + Arrays.toString(arr) + "最小和：" + ans);

    }

    public static int add(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int m = l + ((r - l) >> 1);
        return add(arr, l, m) + add(arr, m + 1, r) + merge(arr, l, m, r);
    }

    public static int merge(int[] arr, int l, int m, int r) {
        int[] helper = new int[r - l + 1];
        int p1 = l;
        int p2 = m + 1;
        int i = 0;
        int ans = 0;
        while (p1 <= m && p2 <= r) {
            // 如果左侧的数小于右侧的数，说明有 r-p2 个数比 p1 位置的数大，ans 累加上 当前 p1* (r - p2 + 1)
            // 如果左侧的数等于右侧的数，右侧的数先移动
            if (arr[p1] < arr[p2]) {
                ans += arr[p1] * (r - p2 + 1);
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
