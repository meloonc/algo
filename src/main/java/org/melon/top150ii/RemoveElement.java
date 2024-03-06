package org.melon.top150ii;

/**
 * <a href="https://leetcode.cn/problems/remove-element/">27. 移除元素</a>
 */
public class RemoveElement {

    public int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {

            if(nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }
}
