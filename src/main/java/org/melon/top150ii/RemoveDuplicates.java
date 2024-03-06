package org.melon.top150ii;


/**
 * <a href="https://leetcode.cn/problems/remove-duplicates-from-sorted-array/">26. 删除排序数组中的重复项</a>
 */
public class RemoveDuplicates {

    public int removeDuplicates(int[] nums) {
        int i = 1;
        for (int j = 1; j < nums.length; j++) {
            // 如果和上一个数不相等
            if (nums[j] != nums[j - 1]) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }
}
