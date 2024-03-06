package org.melon.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/combinations/">组合</a>
 */
public class Combinations {

    public static void main(String[] args) {
        List<List<Integer>> result = new Combinations().combine(4, 2);
        System.out.println(result);
    }

    /**
     * 回溯算法，转换为树形结构
     * 本质上就是 循环+递归
     * 通过递归终止条件剪枝
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backtracking(n, k, 1, path, result);
        return result;
    }

    public void backtracking(int n, int k, int startIndex, List<Integer> path, List<List<Integer>> result) {

        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        } else {
            // 这里可以优化剪枝，由于需要 k 个数进行组合，如果剩余的数+ path 里面的数不够 k 个，可以直接舍弃
            // 比如 n = 4, k = 3，起始位置只有两个种方案，1 和 2 ，如果选到 3 ，无论无何都不够 n=3 个数，即 i <= n - (k - path.size()) + 1
            for (int i = startIndex; i <= n - (k - path.size()) + 1; i++) {
                path.add(i);
                backtracking(n, k, i + 1, path, result);
                // 回溯，进行其他路径的选择
                path.remove(path.size() - 1);
            }
        }
    }

}
