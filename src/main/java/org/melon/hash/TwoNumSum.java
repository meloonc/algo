package org.melon.hash;

import java.util.HashMap;

/**
 * <a href="https://leetcode-cn.com/problems/two-sum/">1. 两数之和</a>
 */
public class TwoNumSum {

    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if(i != j && nums[i] + nums[j] == target){
                    res[0] = i;
                    res[1] = j;
                    return res;
                }
            }
        }

        return res;
    }

    public int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int[] arr = map.get(target - nums[i]);
            if(arr != null){
                return new int[]{arr[0], i};
            }
            map.put(nums[i], new int[]{i});
        }
        return map.entrySet().iterator().next().getValue();
    }

    }
