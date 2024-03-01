package org.melon.top150;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/3sum/">15. 三数之和</a>
 */
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        // 首先排序
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        // 从最小的数开始计算是否三数之和=0
        for (int i = 0; i < nums.length; i++) {
            // 如果当前数据已经大于0 ，后面的数只会更大，三数之和不会=0
            if(nums[i] > 0){
                break;
            }
            // 过滤掉重复数据
            if(i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            // 左右指针
            // 左指针从区间范围内（i+1~nums.length - 1）的第一个数开始
            int j = i + 1;
            // 右指针从区间范围内（i+1~nums.length - 1）的最后一个数开始
            int k = nums.length - 1;
            // 指针没有碰撞
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum > 0) {
                    while (j < k && nums[k] == nums[--k]);
                } else if (sum < 0) {
                    while (j < k && nums[j] == nums[++j]);
                } else {
                    List<Integer> list = Arrays.asList(nums[i], nums[j], nums[k]);
                    result.add(list);
                    // 过滤重复数据
                    while (j < k && nums[j] == nums[++j]);
                    while (j < k && nums[k] == nums[--k]);
                }
            }
        }
        return result;


    }
}
