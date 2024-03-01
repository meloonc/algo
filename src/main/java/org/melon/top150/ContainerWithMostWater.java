package org.melon.top150;

/**
 * <a href="https://leetcode.cn/problems/container-with-most-water/">11. 盛最多水的容器</a>
 */
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int i =0, j = height.length-1;

        int max = 0;
        while (i < j) {
            max = Math.max(max, Math.min(height[i], height[j]) * (j - i));
            // 高度选高的
            // 移动较低的
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return max;

    }


}
