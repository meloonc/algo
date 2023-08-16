package org.melon.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 选择排序
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = arr();
        System.out.println("before sort" + Arrays.toString(arr));
        sort(arr);
        System.out.println("after sort" + Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        // 0～n-1 找到最小，放到 0 位置
        // 1～n-1 找到最小， 放到 1 位置
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int[] arr() {
        int[] ints = new int[10];
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            ints[i] = random.nextInt(100);
        }
        return ints;
    }
}
