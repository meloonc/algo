package org.melon.top150ii;

import java.util.ArrayList;
import java.util.List;

public class MajorityElement2 {

    /**
     * 给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
     * <p>
     * <p>
     * 答案最多两个数
     *
     * @param nums
     * @return
     */
    public List<Integer> majorityElement(int[] nums) {

        int a = 0, as = 0, b = 0, bs = 0;
        // 投票过程
        for (int num : nums) {
            // a 票数为0，选一个数
            if (as == 0) {
                a = num;
                as++;
                // b 票数为0，选一个数
            } else if (bs == 0) {
                b = num;
                bs++;
                // a 票
            } else if (a == num) {
                as++;
                // b 票
            } else if (b == num) {
                bs++;
            } else {
                // 不是a 和 b，票数都要-1
                bs--;
                as--;
            }
        }

        // 大于 n/3 的数在 a b 里面，核算一下真正的票数
        as = 0;
        bs = 0;

        for (int num : nums) {
            if (num == a) {
                as++;
            }
            if (num == b) {
                bs++;
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        if (as > nums.length / 3) {
            result.add(a);
        }
        if (bs > nums.length / 3) {
            result.add(b);
        }
        return result;


    }
}
