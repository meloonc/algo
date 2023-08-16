package org.melon.search;

/**
 * 二分查找
 */
public class BinarySearch {

    public static boolean exist(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int l = 0;
        int r = arr.length - 1;
        while (l < r) {
            int mid = l + ((r - l) >> 2);

            if (arr[mid] == num) {
                return true;
            }
            if (arr[mid] > num) {
                r = mid - 1;
            }
            if (arr[mid] < num) {
                l = mid + 1;
            }

        }
        return arr[l] == num;
    }

    public static void main(String[] args) {
        int[] arr = {0, 2, 3, 7, 8, 9};
        boolean exist = exist(arr, 0);
        System.out.println(exist);
    }

}
