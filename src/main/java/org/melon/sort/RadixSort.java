package org.melon.sort;

import java.util.Arrays;

import static org.melon.sort.SelectionSort.arr;

/**
 * 基数排序 O(N)
 * 只能用于正整数，有局限性
 */
public class RadixSort {

    public static void main(String[] args) {
        int[] arr = arr();
        System.out.println("before sort" + Arrays.toString(arr));
        radixSort(arr);
        System.out.println("after sort" + Arrays.toString(arr));
    }

    // 基数排序
    public static void radixSort(int[] arr) {
        // 获取数组中的最大值
        int max = 0;
        for (int i : arr) {
            max = Math.max(max, i);
        }
        // 计算最大位数
        int maxBits = maxBits(max);
        for (int i = 0; i < maxBits; i++) {
            process(arr, i);
        }
    }

    public static void process(int[] arr, int digit){
        // 构建一个辅助数组
        int[] help = new int[arr.length];
        // 用来记录每一位的数量，例如个位上有几个 1 ，几个 2
        int[]  count = new int[10];
        for (int i = 0; i < arr.length; i++) {
            count[digits(arr[i], digit)]++;
        }
        // 将 count 数组转化位前缀和数组，即个位上有几个数大于1 ，大于2
        for (int i = 0; i < count.length; i++) {
            if(i != 0) {
                count[i] = count[i] + count[i - 1];
            }
        }
        // 从右往左遍历数组
        for (int i = arr.length-1; i >=0 ; i--) {
            // 计算位上的值
            int digits = digits(arr[i], digit);
            // 将 arr[i] 放入到 help 数组中，位置是最靠右的位置
            help[count[digits] - 1] = arr[i];
            // 位上的数 --
            count[digits]--;
        }
        // 将 help 数组复制到 arr 数组中
        for (int i = 0; i < help.length; i++) {
            arr[i] = help[i];
        }
    }

    // 计算数字在某个位的值，比如 10 在个位上是 0 ，在十位上是1
    public static int digits(int n, int i) {
        return n / (int) Math.pow(10, i) % 10;
    }

    // 计算数字有几位
    public static int maxBits(int n) {
        return (int) (Math.log10(n) + 1);
    }

}
