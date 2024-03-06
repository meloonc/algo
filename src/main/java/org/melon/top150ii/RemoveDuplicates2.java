package org.melon.top150ii;

/**
 * <a href="https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/">LC 80</a>
 */
public class RemoveDuplicates2 {
    /**
     * 数组中的数字最多可以保留两个
     * 即数组中前两个可以直接保留
     * 定义两个指针， slow 用来控制返回结果，fast 用来遍历数组
     * 后面的数字与 slow -2 比较，如果相等就跳过，不相等就需要记录，也就是调整位置
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {

        int fast = 2;
        int slow = 2;
        while (fast < nums.length) {
            if (nums[fast] != nums[slow - 2]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}
