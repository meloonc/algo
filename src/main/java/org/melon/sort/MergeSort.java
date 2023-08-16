package org.melon.sort;

import java.util.Arrays;

import static org.melon.sort.InsertSort.arr;

/**
 * 归并排序 O(N*logN)
 * 使用递归，是左边有序，右边有序，最后合并在一快，整体有序
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = arr();
        System.out.println("before sort" + Arrays.toString(arr));
        mergeSortWithRecursion(arr);
        System.out.println("after sort" + Arrays.toString(arr));
    }

    // 递归实现归并排序
    public static void mergeSortWithRecursion(int[] arr) {
        recursion(arr, 0, arr.length - 1);
    }

    public static void recursion(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int m = l + ((r - l) >> 1);
        recursion(arr, l, m);
        recursion(arr, m + 1, r);
        merge(arr, l, m, r);
    }

    public static void merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        while (p1 <= m && p2 <= r) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++]: arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[l + j] = help[j];
        }

    }

    // 循环实现归并排序
    public void mergeSortWithLoop(int[] arr) {

    }
}
