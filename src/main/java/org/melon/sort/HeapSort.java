
package org.melon.sort;

import java.util.Arrays;

import static org.melon.sort.SelectionSort.arr;
import static org.melon.sort.SelectionSort.swap;

/**
 * 堆排序
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] arr = arr();
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        heapSort2(arr);
        System.out.println(Arrays.toString(arr));
    }

    // 堆排序
//    public static void headSort(int[] arr) {
//        // 从上往下调整为大根堆 O(N*logN)
//        for (int i = 0; i < arr.length; i++) {
//            heapInsert(arr, i);
//        }
//
//        // 从下往上调整为大根堆 O(N)
//        for (int i = arr.length - 1; i >= 0; i--) {
//            heapify(arr, i, arr.length);
//        }
//
//        // 然后将数组中最后的元素和第一个元素交换位置，同时调整剩余的数字为大根堆，直到堆的大小为1
//        int heapSize = arr.length;
//        // 将最大值和最后一个元素交换位置，堆大小-1
//        swap(arr, 0, --heapSize);
//        while (heapSize > 0) {
//            heapify(arr, 0, heapSize);
//            swap(arr, 0, --heapSize);
//        }
//    }
//
//    // 构建大根堆
//    public static void heapInsert(int[] arr, int index) {
//        if (index == 0) {
//            return;
//        }
//        // 父节点的位置
//        int parent = (index - 1) / 2;
//        // 如果当前位置>父节点，交换位置
//        if (arr[index] > arr[parent]) {
//            swap(arr, index, parent);
//            // 递归调整
//            heapInsert(arr, parent);
//        }
//    }
//
//    // 维持大根堆
//    public static void heapify(int[] arr, int index, int heapSize) {
//        // 左边子节点的位置
//        int left = 2 * index + 1;
//        while (left < heapSize) {
//            // 找到大的子节点
//            int max = left + 1 < heapSize && arr[left] < arr[left + 1] ? left + 1 : left;
//            max = arr[index] > arr[max] ? index : max;
//            // 如果当前位置就是最大的节点，说明已经是大根堆了
//            if (max == index) {
//                break;
//            }
//            // 与子节点交换位置
//            swap(arr, index, max);
//            // 当前位置来到交换后的位置
//            index = max;
//            // 找到下一个层级的子节点
//            left = 2 * index + 1;
//        }
//    }

    public static void heapSort(int[] arr) {


    }

    /**
     * Inserts an element into a heap at the specified index.
     *
     * @param arr   the heap array
     * @param index the index where the element should be inserted
     */
    public static void heapInsert(int[] arr, int index) {
        // 获取父节点的位置
        int parent = (index - 1) / 2;
        // 判断是否比父节点大，如果大就交换位置
        if (index != 0 && arr[index] > arr[parent]) {
            swap(arr, index, parent);
            // 父节点继续向上比较
            heapInsert(arr, parent);
        }
    }

    public static void heapify(int[] arr, int index, int heapSize) {
        // 找到左边子节点的位置
        // 如果子节点的位置没有溢出
        int left = index * 2 + 1;
        while (left < heapSize) {
            // 找到大的子节点和 index 位置比较
            int max = left + 1 < heapSize ? (arr[left + 1] > arr[left] ? left + 1 : left) : left;
            max = arr[max] > arr[index] ? max : index;
            // 如果当前位置就是最大的节点，说明已经是大根堆了
            if (max == index) {
                break;
            }
            swap(arr, index, max);
            index = max;
            left = index * 2 + 1;
            heapify(arr, index, heapSize);
        }
    }

    public static void heapSort2(int[] arr) {
        // 实现层级排序算法
        int n = arr.length;
//        for (int i = 0; i < arr.length; i++) {
//            heapInsert(arr, i );
//        }
        for (int i = n -1; i >= 0; i--) {
            heapify(arr, i, n);
        }
        // 堆的最大位置与 0 位置交换，然后维持大根堆
        for (int i = n - 1; i >= 0; i--) {
            swap(arr, 0, i);
            heapify(arr, 0, i);
        }
    }



}