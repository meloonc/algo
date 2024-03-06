package org.melon.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/combination-sum/">39. 组合总和</a>
 */
public class CombinationSum {

    public static void main(String[] args) {
        List<List<Integer>> result = new CombinationSum().combinationSum(new int[]{2, 3, 6, 7}, 7);
        System.out.println(result);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(candidates);
        backtracking(candidates, target, 0, path, result, 0);
        return result;
    }


    public void backtracking(int[] candidates, int target, int startIndex, List<Integer> path, List<List<Integer>> result, int sum) {
        if (sum == target) {
            result.add(new ArrayList<>(path));
            return;
        } else {
            for (int i = startIndex; i < candidates.length; i++) {
                sum += candidates[i];
                if (sum > target) {
                    continue;
                }
                path.add(candidates[i]);
                backtracking(candidates, target, i, path, result, sum);
                path.remove(path.size() - 1);
                sum -= candidates[i];
            }
        }
    }
}
