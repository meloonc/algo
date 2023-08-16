package org.melon.search;

/**
 * 找出局部最小值
 * 在一组无序的数中，找到一个比相邻的数字都小的数
 */
public class LocalMinima {

    public static void main(String[] args) {
        int[] arr = {13, 12, 1, 4, 2, 5, 13};
        int search = search(arr);
        System.out.println(search);
    }

    public static int search(int[] arr) {
        // 单独验证下 0 位置，如果小于 1 为位置 返回0
        if (arr[1] > arr[0]) {
            return 0;
        }
        // 单独验证 n-1 位置，如果小于 n-2 ，返回 n-1
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int l = 1;
        int r = arr.length - 2;


        /**
         * 1~n-1 范围肯定有局部最小值，使用二分查找
         * 如果中间位置的数 > MID +1 位置的数，说明右侧一定有一个局部最小值，则丢弃掉左侧的数据，此时右侧的数据曲线是下降的, 将 l 置成 mid + 1
         * 如果中间位置的数 > MID -1 位置的数，说明左侧一定有一个局部最小值，则丢弃掉右侧的数据，此时左侧的数据曲线是上升的， 将 r 置成 mid - 1
         */
        while (l < r) {
            int mid = (l + r) / 2;
            // 如果 mid 大于左边的数，说明在左边肯定有局部最小值。舍弃掉右边的数
            if (arr[mid] > arr[mid - 1]) {
                r = mid - 1;
            // 如果大于右边的数，说明在左边肯定有局部最小值，舍弃掉右边的数
            } else if (arr[mid] > arr[mid + 1]) {
                l = mid + 1;
            } else {
                return mid;
            }
        }
        return l;
    }
}
