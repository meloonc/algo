package org.melon.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 插入排序
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] arr = arr();
        System.out.println("before sort" + Arrays.toString(arr));
        sort(arr);
        System.out.println("after sort" + Arrays.toString(arr));
    }

    /**
     * 插入排序认为指定位置之前的数据都是有序的，如果相邻的数据无序，调整位置直到 0 位置，如果相邻的数据有序则不调整数序
     * 类似于整理扑克牌
     */
    public static void sort(int[] arr) {
        // 从 1 开始遍历，因为 0 是认为有序的
        for (int i = 1; i < arr.length; i++) {
            // 和之前的数相比较，如果后面的数据小于前面的数据则交换位置
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j + 1] < arr[j]) {
                    swap(arr, j, j + 1);
                }
            }
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
