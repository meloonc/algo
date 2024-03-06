package org.melon.top150ii;

/**
 * <a href="https://leetcode.cn/problems/merge-sorted-array/">88. 合并两个有序数组</a>
 */
public class MergeTwoSortedArray {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 由于 nums1 数组后部分是空的，所以指针从后向前遍历，大的数字放 nums1 后面
        // 第一部分数的指针
        int p1 = m - 1;
        // 第二个部分数的指针
        int p2 = n - 1;
        // 全局数组的指针
        int p = m + n - 1;
        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] > nums2[p2]) {
                nums1[p] = nums1[p1];
                p1--;
            } else {
                nums1[p] = nums2[p2];
                p2--;
            }
            p--;
        }
        // 如果 nums2 的数还没有全部迁移，需要直接拷贝到 nums1 的前面部分
        if (p2 >= 0) {
            System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
        }


    }


}
