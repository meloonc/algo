package org.melon.top150;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/h-index/">274. H 指数</a>
 */
public class HIndex {

    public static void main(String[] args) {
        HIndex hIndex = new HIndex();
        int[] arr = {1,3,1};
        System.out.println(hIndex.hIndex(arr));
    }

    public int hIndex(int[] citations) {
        int n = citations.length;

        int[] cnt = new int[n + 1];
        for (int i : citations) {
            // 如果当前引用数量超过了论文数组的数量，视为论文最大数量的 h 指数
            cnt[Math.min(i, n)]++;
        }
        int count = 0;
        for (int i = n; i >= 0; i--) {
            count += cnt[i];
            if (count >= i) {
                return i;
            }
        }
        return 0;
    }
}
