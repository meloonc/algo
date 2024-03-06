package org.melon.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/combination-sum-iii/">216. 组合总和 III</a>
 */
public class CombinationSum3 {

    public static void main(String[] args) {
        List<List<Integer>> result = new CombinationSum3().combinationSum3(3, 7);
        System.out.println(result);
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backtracking(n, k, 1, path, result, 0);
        return result;
    }

    public void backtracking(int n, int k, int startIndex, List<Integer> path, List<List<Integer>> result, Integer sum) {
        if (path.size() == k && sum == n) {
            result.add(new ArrayList<>(path));
            return;
        } else {
            for (int i = startIndex; i <= 9; i++) {
                int newSum = sum + i;
                if (path.size() + 1 == k && newSum != n) {
                    continue;
                }
                path.add(i);
                backtracking(n, k, i + 1, path, result, newSum);
                path.remove(path.size() - 1);
            }
        }
    }

}
