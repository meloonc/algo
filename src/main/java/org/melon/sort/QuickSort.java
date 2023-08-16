package org.melon.sort;

import java.util.Arrays;

/**
 * 快速排序
 *
 */
public class QuickSort {

    public static void main(String[] args) {
        // qucik sort
        int[] arr = { 0, 12, 4, 2, 8, 1, 2, 9 };
        // netherlands(arr, 4);
        // System.out.println(Arrays.toString(arr));
        int[] equalArea = netherlands2(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(equalArea));
        // quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    // 荷兰国旗问题，给定一个数组，并给一个数字，使数组小于该数放在左边，大于该数放在右边，等于该数放中间
    // {0,12,4,2,8,1,2,9} 给定 4 ， 数组的顺序变成 {0,2,1,2,4,12,8,9}
    public static void netherlands(int[] arr, int num) {
        // 大于区域的结束位置，向左边扩
        int more = arr.length;
        // 小于区域的初始位置， 向右边扩
        int less = -1;
        int index = 0;
        while (index < more) {
            // 如果当前数小于 num 就放在左边
            if (arr[index] < num) {
                swap(arr, index++, ++less);
            } else if (arr[index] == num) {
                index++;
            } else {
                swap(arr, index, --more);
            }
        }

    }

    // 交换顺序
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 快排的思路就是，在数组中随机选一个数字，进行荷兰国旗，指定的数字就排好序了
    // 荷兰国旗需要返回指定数的下标，下表范围的数是有序的
    // 递归调用荷兰国旗算法，最终整个数组有序
    public static void quickSort(int[] arr) {
        process(arr, 0, arr.length - 1);

    }

    public static void process(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        // 随机选一个数， 使快排的复杂度变成 O(N*logN)， 这是期望的时间复杂度，概率事件
        int index = (int) (Math.random() * (r - l + 1)) + l;
        swap(arr, l, index);
        int[] equalArea = netherlands2(arr, l, r);
        process(arr, l, equalArea[0] - 1);
        process(arr, equalArea[1] + 1, r);
    }

    // 荷兰国旗，数字是数组中指定范围的最后一个数
    public static int[] netherlands2(int[] arr, int l, int r) {

        int[] res = new int[2];
        int num = arr[r];
        int more = r;
        int less = l - 1;
        int index = l;
        while (index < more) {
            // 如果当前数小于 num 就放在左边
            if (arr[index] < num) {
                // 小于区间
                swap(arr, index++, ++less);
            } else if (arr[index] == num) {
                // 等于区间
                index++;
            } else {
                // 大于区间
                swap(arr, index, --more);
            }
        }
        // 指定出来的数要放在大于区间的前面
        swap(arr, more, r);
        // less+1 才是等于区间
        res[0] = less + 1;
        // 由于换过位置， more 就是等于区间
        res[1] = more;
        return res;
    }

}
