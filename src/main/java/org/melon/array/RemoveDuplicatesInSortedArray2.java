package org.melon.array;

/**
 * <a href="https://leetcode.cn/problems/remove-duplicates-from-sorted-array-ii/">
 * 80. 删除有序数组中的重复项II
 * </a>
 */
public class RemoveDuplicatesInSortedArray2 {

    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 2, 2, 3};

        RemoveDuplicatesInSortedArray2 solution = new RemoveDuplicatesInSortedArray2();
        int i = solution.removeDuplicates(arr);
        System.out.println(i);
    }


    public int removeDuplicates(int[] nums) {
        if (nums == null) {
            return 0;
        }
        return process(nums, 2);

    }

    public int process(int[] nums, int k) {
        if (nums.length <= k) {
            return nums.length;
        }
        int fast = k;
        int slow = k;
        while (fast < nums.length) {
            if (nums[fast] != nums[slow - k]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }

        return slow;
    }
}
