package org.melon.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 冒泡排序
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = arr();
        System.out.println("before sort" + Arrays.toString(arr));
        sort(arr);
        System.out.println("after sort" + Arrays.toString(arr));
    }

    /**
     * 相邻两个数比较，大的数排在最后面，循环一次后，最大的数在最后的位置上
     */
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }
            }
        }
    }

    /**
     * 使用异或运算不创建额外变量即可交换数据
     * 1. 0 ^ a = a
     * 2. a ^ a = 0
     * 3. 异或运算与数据位置无关，同一组数据异或的结果一直相等
     * 4. 两个数字位于不同内存区域，否则异或运算后结果 = 0
     */
    public static void swap(int[] arr, int i, int j) {
//        int temp = arr[i];
//        arr[i] = arr[j];
//        arr[j] = temp;
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];  // arr[i] ^ arr[j] ^ arr[j] -> 0 ^ arr[j] -> arr[j] = arr[i]
        arr[i] = arr[i] ^ arr[j];  // arr[i] ^ arr[i] ^ arr[j] -> 0 ^ arr[i] -> arr[i] = arr[j]

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
