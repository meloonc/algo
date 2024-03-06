package org.melon.top150ii;

/**
 * <a href="https://leetcode.cn/problems/rotate-array/">189. 旋转数组</a>
 */
public class RotateArray {

    public void rotate(int[] nums, int k) {
        // k 是真正旋转的次数
        k = k % nums.length;
        // 假如 k=2
        // 反转数组 [1,2,3,4,5,6,7]
        // 1.反转整个数组 [7,6,5,4,3,2,1]
        // 2.反转 0 ~ k-1 [6,7,5,4,3,2,1]
        // 3.反转 k ~ n-1 [6,7,1,2,3,4,5]
        int n = nums.length - 1;
        reverse(nums, 0, n);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n);
    }


    /**
     * 反转数组上 i ~ j 位置的元素
     *
     * @param nums
     * @param i
     * @param j
     */

    public void reverse(int[] nums, int i, int j) {

        while (i <= j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }

    }
}
