package org.melon.top150;

/**
 * <a href=https://leetcode.cn/problems/merge-sorted-array/description/>合并两个有序数组</a>
 */
public class MergeSortedArray {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p = m - 1;
        int q = n - 1;
        int i = m + n - 1;
        while (p >= 0 && q >= 0) {
            nums1[i--] = nums1[p] > nums2[q] ? nums1[p--] : nums2[q--];
        }

        System.arraycopy(nums2, 0, nums1, 0, q + 1);
    }
}
