package org.melon.merge;

import java.util.Arrays;

import static org.melon.sort.SelectionSort.arr;

/**
 * 逆序对
 * 如果右侧有比自己小的数形成逆序对
 * 返回逆序对的总数
 */
public class DescPair {

    public static void main(String[] args) {
        int[] arr = arr();
        System.out.println("求和之前:" + Arrays.toString(arr));

        int merge = merge(arr, 0, arr.length - 1);
        System.out.println("求和之后:" + Arrays.toString(arr));
        System.out.println("逆序对总数：" + merge);
    }

    public static int merge(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int m = l + ((r - l) >> 1);
        return merge(arr, l, m) +
                merge(arr, m + 1, r) +
                add(arr, l, m, r);
    }

    public static int add(int[] arr, int l, int m, int r) {
        int[] helper = new int[r - l + 1];
        int p1 = m;
        int p2 = r;
        int i = r - l;
        int ans = 0;
        // 反着遍历
        while (p1 >= l && p2 >= m + 1) {
            // 如果左边的数 <= 右边的数，右边的数放进辅助数组，准备排有序
            if (arr[p1] <= arr[p2]) {
                helper[i--] = arr[p2--];
            } else {
                // 逆序对个数，左边的数>右边的数
                helper[i--] = arr[p1--];
                ans += (p2 - m);
            }
        }
        while (p1 >= l) {
            helper[i--] = arr[p1--];
        }
        while (p2 >= m + 1) {
            helper[i--] = arr[p2--];
        }

        for (i = 0; i < helper.length; i++) {
            arr[l + i] = helper[i];
        }
        return ans;
    }
}
