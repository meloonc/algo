package org.melon.top150;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/jump-game/">55. 跳跃游戏</a>
 */
public class CanJump {

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 0, 4};
        CanJump canJump = new CanJump();
        boolean couldJump = canJump.canJump(nums);
        System.out.println(couldJump);
    }

    public boolean canJump(int[] nums) {
        int[] cache = new int[nums.length];
        Arrays.fill(cache, -1);
        return process(nums, 0, nums[0], cache);
    }

    public boolean process(int[] nums, int index, int step, int[] cache) {
        if (cache[index] != -1) {
            return cache[index] == 1;
        }
        if (nums[index] == 0 && index != nums.length - 1) {
            return false;
        }
        if (index == nums.length - 1 || index + nums[index] >= nums.length - 1) {
            return true;
        }
        boolean res = false;
        while (step > 0) {
            res = process(nums, ++index, nums[index], cache);
            step--;
            if (res) {
                break;
            }
        }
        cache[index] = res ? 1 : 0;
        return res;
    }

    public boolean canJump2(int[] nums) {
        int far = 0;
        for (int i = 0; i < nums.length; i++) {
            // 说明最远距离不能到达
            if (i > far) {
                return false;
            }
            // 最远位置
            far = Math.max(far, i + nums[i]);
            // 如果已经能到最远位置
            if (far >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }

}
