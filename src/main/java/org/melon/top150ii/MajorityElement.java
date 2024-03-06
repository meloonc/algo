package org.melon.top150ii;

/**
 * <a href="https://leetcode.cn/problems/majority-element/">169. 多数元素</a>
 */
public class MajorityElement {

    /**
     * 众数
     * 采用投票法，数字相同票数+1.不同票数-1
     * 由于是多数元素，遍历结束后，票数一定大于0，多数元素也就找到了
     * @param nums
     * @return
     */

    public int majorityElement(int[] nums) {

        int x = 0, votes = 0;
        for (int num : nums) {
            if (votes == 0) {
                x = num;
                votes++;

            } else {
                votes += x == num ? 1 : -1;
            }

        }

        return x;

    }

}
