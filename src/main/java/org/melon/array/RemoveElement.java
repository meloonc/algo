package org.melon.array;

/**
 * <a href="https://leetcode-cn.com/problems/remove-element/">移除元素</a>
 */
public class RemoveElement {

    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int end = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if(nums[i] == val) {
                swap(nums, i, end);
                end --;
            }
        }
        return end + 1;

    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}
