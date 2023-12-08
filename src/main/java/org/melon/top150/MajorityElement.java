package org.melon.top150;

/**
 * <a href="https://leetcode.cn/problems/majority-element/">169. Majority Element</a>
 * <p>
 * 摩尔投票 众数
 */
public class MajorityElement {
    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int num = nums[0], count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                num = nums[i];
            }
            if (nums[i] == num) {
                count++;
            } else {
                count--;
            }
        }

        
        return num;
    }


}
