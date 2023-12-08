package org.melon.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/triangle/">120. 三角形最小路径和</a>
 */
public class MinimumTotal {



    public int minimumTotal(List<List<Integer>> triangle) {
        int size = triangle.size();
        return process(triangle, size - 1, size - 1);
    }

    /**
     * @param triangle
     * @param index
     * @param level
     * @return
     */
    public int process(List<List<Integer>> triangle, int index, int level) {
//        if (index < 0 || index > level) {
//            return Integer.MAX_VALUE;
//        }
        // 到达第 0 层，没有其他选择
        if (level == 0) {
            return triangle.get(0).get(0);
        }
        // index 的最大值与 level 相等
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < index; i++) {
            int p1 = Integer.MAX_VALUE;
            if (i != 0) {
                p1 = triangle.get(level).get(i) + process(triangle, index, level - 1);
            }
            int p2 = Integer.MAX_VALUE;
            if (i != index - 1) {
                p2 = triangle.get(level).get(i) + process(triangle, index - 1, level - 1);
            }
            int p = Math.min(p1, p2);
            min = Math.min(min, p);
        }
        return min;
    }

    public static void main(String[] args) {
        MinimumTotal minimumTotal = new MinimumTotal();
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(2);
        lists.add(list1);
        List<Integer> list2 = new ArrayList<>();
        list2.add(3);
        list2.add(4);
        lists.add(list2);
        List<Integer> list3 = new ArrayList<>();
        list3.add(6);
        list3.add(5);
        list3.add(7);
        lists.add(list3);
        List<Integer> list4 = new ArrayList<>();
        list4.add(4);
        list4.add(1);
        list4.add(8);
        list4.add(3);
        lists.add(list4);
        int i = minimumTotal.dp(lists);



    }

    public int dp(List<List<Integer>> triangle) {
        int size = triangle.size();
        int[][] dp = new int[size][size];
        dp[0][0] = triangle.get(0).get(0);
        for (int level = 1; level < size; level++) {
            for (int index = 0; index <= level; index++) {
                if (index == 0) {
                    dp[level][index] = dp[level - 1][index] + triangle.get(level).get(index);
                } else if (index == level) {
                    dp[level][index] = dp[level - 1][index - 1] + triangle.get(level).get(index);
                } else {
                    dp[level][index] = Math.min(dp[level - 1][index], dp[level - 1][index - 1]) + triangle.get(level).get(index);
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            res = Math.min(res, dp[size - 1][i]);
        }
        return res;
    }
}
