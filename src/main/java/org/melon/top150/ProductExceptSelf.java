package org.melon.top150;

/**
 * <a href="https://leetcode.cn/problems/product-of-array-except-self/">238. Product of Array Except Self</a>
 */
public class ProductExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        result[0] = 1;
        // 正序遍历，计算左边的乘积
        for (int i = 1; i < nums.length; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        // 倒序遍历，计算右边的乘积
        int right = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            result[i] = result[i] * right;
            right *= nums[i];
        }

        return result;
    }
}
