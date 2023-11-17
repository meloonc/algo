package org.melon.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/majority-element-ii/">229. 多数元素 II</a>
 * <p>
 * 摩尔投票
 */
public class MajorityElement2 {
    public List<Integer> majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }

        int a = 0, b = 0;
        int as = 0, bs = 0;
        for (int num : nums) {
            if (num == a) {
                as++;
            } else if (num == b) {
                bs++;
            } else if (as == 0) {
                a = num;
                as = 1;
            } else if (bs == 0) {
                b = num;
                bs = 1;
            } else {
                as--;
                bs--;
            }
        }
        as = 0;
        bs = 0;
        for (int num : nums) {
            if (num == a) {
                as++;
            } else if (num == b) {
                bs++;
            }
        }
        List<Integer> list = new ArrayList<>();
        if (as > nums.length / 3) {

            list.add(a);
        }
        if (bs > nums.length / 3) {
            list.add(b);
        }
        return list;
    }


}
