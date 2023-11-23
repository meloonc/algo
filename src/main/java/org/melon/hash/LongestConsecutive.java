package org.melon.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LongestConsecutive {

    public int longestConsecutive(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        UnionFind unionFind = new UnionFind(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if(nums[i] + 1 == nums[i + 1]) {
                unionFind.union(nums[i], nums[i] + 1);
            }
        }
        return unionFind.max;
    }


    class UnionFind {
        private HashMap<Integer, Integer> father = new HashMap<Integer, Integer>();

        private HashMap<Integer, Integer> size = new HashMap<>();

        private Integer max = 1;

        UnionFind(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                father.put(nums[i], nums[i]);
                size.put(nums[i], 1);
            }
        }

        int find(int x) {
            List<Integer> temp = new ArrayList<>();
            int parent = father.get(x);
            while (parent != father.get(parent)) {
                parent = father.get(parent);
                temp.add(parent);
            }
            for (Integer i : temp) {
                father.put(i, parent);
            }
            return parent;
        }

        void union(int x, int y) {
            int fa_x = find(x);
            int fa_y = find(y);
            if (fa_x != fa_y) {
                if (size.get(fa_x) > size.get(fa_y)) {
                    father.put(fa_y, fa_x);
                    size.put(fa_x, size.get(fa_x) + size.get(fa_y));
                    max = Math.max(max, size.get(fa_x));

                } else {
                    father.put(fa_x, fa_y);
                    size.put(fa_y, size.get(fa_x) + size.get(fa_y));
                    max = Math.max(max, size.get(fa_y));
                }
            }

        }
    }


}
