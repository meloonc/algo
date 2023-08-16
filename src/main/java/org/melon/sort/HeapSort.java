
package org.melon.sort;

import static org.melon.sort.SelectionSort.swap;

/**
 * 堆排序
 */
public class HeapSort {

    public static void main(String[] args) {

    }

    // 堆排序
    public static void headSort(int[] arr) {
        // 调整为大根堆
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        // 然后将数组中最后的元素和第一个元素交换位置，同时调整剩余的数字为大根堆，直到堆的大小为1
        int heapSize = arr.length;
        // 将最大值和最后一个元素交换位置，堆大小-1
        swap(arr, 0, --heapSize);

    }

    // 构建大根堆
    public static void heapInsert(int[] arr, int index) {
        if (index == 0) {
            return;
        }
        // 父节点的位置
        int parent = (index - 1) / 2;
        // 如果当前位置>父节点，交换位置
        if (arr[index] > arr[parent]) {
            swap(arr, index, parent);
            // 递归调整
            heapInsert(arr, parent);
        }
    }

    // 维持大根堆
    public static void heapify(int[] arr, int index, int heapSize) {
        // 左边子节点的位置
        int left = 2 * index + 1;
        while (left < heapSize) {
            // 找到大的子节点
            int max = left + 1 < heapSize && arr[left] < arr[left + 1] ? left + 1 : left;
            max = arr[index] > arr[max] ? index : max;
            // 如果当前位置就是最大的节点，说明已经是大根堆了
            if (max == index) {
                break;
            }
            // 与子节点交换位置
            swap(arr, index, max);
            // 当前位置来到交换后的位置
            index = max;
            // 找到下一个层级的子节点
            left = 2 * index + 1;
        }
    }

}